package com.itacademy.cms.model.dto;

import com.itacademy.cms.model.Course;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

  public List<Course> courses;
  private String categoryName;
}
