package com.itacademy.cms.model.enums;

public enum Language {
  ENGLISH("English"), RUSSIAN("Russian"), UKRAINIAN("Ukrainian"), SPANISH("Spanish"),
  ITALIAN("Italian"), GERMAN("German");
  private final String name;

  Language(String name) {
    this.name = name;
  }

  public static Language getLanguageByString(String name) {
    for (Language language : values()) {
      if (language.getName().equals(name)) {
        return language;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }
}