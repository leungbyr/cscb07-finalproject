package com.bank.users;

import android.content.Context;

import com.bank.database.android.DatabaseHelper;
import com.bank.generics.RoleMap;
import com.bank.security.PasswordHelpers;

public abstract class UserImpl implements User {
  private int id;
  private String name;
  private int age;
  private String address;
  private int roleId;
  private boolean authenticated;
  private Context context;
  private DatabaseHelper dbHelper;

  /**
   * Creates a User object.
   * @param id the ID of the user
   * @param name the name of the user
   * @param age the age of the user
   * @param address the address of the user
   */
  public UserImpl(Context context, int id, String name, int age, String address) {
    dbHelper = new DatabaseHelper(context);
    this.context = context;
    this.setId(id);
    this.setName(name);
    this.setAge(age);
    this.setAddress(address);
  }
  
  /**
   * Creates a User object.
   * @param id the ID of the user
   * @param name the name of the user
   * @param age the age of the user
   * @param address the address of the user
   * @param authenticated true if user is authenticated, false otherwise
   */
  public UserImpl(Context context, int id, String name, int age, String address,
                  boolean authenticated) {
    dbHelper = new DatabaseHelper(context);
    this.context = context;
    this.setId(id);
    this.setName(name);
    this.setAge(age);
    this.setAddress(address);
    this.authenticated = authenticated;
  }
  
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getAddress() {
    return this.address;
  }

  public int getRoleId() {
    return this.roleId;
  }
  
  /**
   * Authenticate user using a password.
   * @param password the input password
   * @return true if the password matches the user's password, false otherwise
   */
  public final boolean authenticated(String password) {
    this.authenticated = PasswordHelpers.comparePassword(dbHelper.getPassword(this.id), password);
    return this.authenticated;
  }
  
  /**
   * Sets the roleId of the account according to the database.
   * @param role the name of the role
   */
  protected void setRoleId(String role) {
    this.roleId = RoleMap.getInstance(this.context).getRoleId(role);
  }

  protected Context getContext() {
    return this.context;
  }
}
