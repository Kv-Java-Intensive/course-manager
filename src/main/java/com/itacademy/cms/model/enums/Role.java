package com.itacademy.cms.model.enums;

public enum Role {
  USER("USER"), AUTHOR("AUTHOR"), ADMIN("ADMIN");
  public String name;

  Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
