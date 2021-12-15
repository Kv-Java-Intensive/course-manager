package com.itacademy.cms.model.dto;

import com.itacademy.cms.model.Course;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupGetDto {
  private String name;

  private LocalDateTime startDate;

  private int capacity;

  private Course course;

}
