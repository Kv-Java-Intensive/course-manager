package com.itacademy.cms.exeption;

public class CourseNotFoundException extends Exception {
  public CourseNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
