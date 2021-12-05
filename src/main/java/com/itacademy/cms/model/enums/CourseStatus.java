package com.itacademy.cms.model.enums;

public enum CourseStatus {
  FINISHED("Finished"), DEFAULT("Default"), IN_WISHLIST("InWishList"), IN_PROGRESS("InProgress");
  private String name;

  CourseStatus(String name) {
    this.name = name;
  }

  public static CourseStatus getCourseStatusByString(String name) {
    for (CourseStatus courseStatus : values()) {
      if (courseStatus.getName().equals(name)) {
        return courseStatus;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}