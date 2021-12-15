package com.itacademy.cms.model.dto;

import com.itacademy.cms.model.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleDto {

  private int lessonNumber;

  private String description;

  private String content;
}
