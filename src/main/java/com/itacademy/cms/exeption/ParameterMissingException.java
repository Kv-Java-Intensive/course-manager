package com.itacademy.cms.exeption;

public class ParameterMissingException extends RuntimeException {
  public ParameterMissingException(String id) {
    super(id);
  }
}
