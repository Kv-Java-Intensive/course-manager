package com.itacademy.cms.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ModuleGetDto {
  private int lessonNumber;

  private String description;

  private String content;
}
