package com.itacademy.cms.exeption;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}

