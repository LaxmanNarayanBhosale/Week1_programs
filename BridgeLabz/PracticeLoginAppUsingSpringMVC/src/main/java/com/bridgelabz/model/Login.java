package com.bridgelabz.model;

public class Login {

  static {
    System.out.println("Login POJO class::static block");
  }

  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
