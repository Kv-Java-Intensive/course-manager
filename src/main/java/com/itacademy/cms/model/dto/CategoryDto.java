package com.itacademy.cms.model.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

  public List<CourseGetDto> courses;
  private String categoryName;
}
