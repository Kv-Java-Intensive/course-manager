package com.itacademy.cms.exeption;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
