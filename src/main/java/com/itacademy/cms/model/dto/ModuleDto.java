package com.itacademy.cms.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleDto {
  private CoursePostDto course;

  private int lessonNumber;

  private String description;

  private String content;
}
