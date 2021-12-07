package com.itacademy.cms.exeption;

public class CategoryNotFoundException extends Exception {

  public CategoryNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
