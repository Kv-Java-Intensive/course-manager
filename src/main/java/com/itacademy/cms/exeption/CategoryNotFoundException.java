package com.itacademy.cms.exeption;

public class CategoryNotFoundException extends RuntimeException {
  public CategoryNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
