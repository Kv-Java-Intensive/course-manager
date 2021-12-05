package com.itacademy.cms.exeption;

public class UserNotFoundException extends Exception {
  public UserNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
