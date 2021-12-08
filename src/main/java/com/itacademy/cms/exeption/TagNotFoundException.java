package com.itacademy.cms.exeption;


public class TagNotFoundException extends RuntimeException {

  public TagNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
