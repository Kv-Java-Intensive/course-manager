package com.itacademy.cms.model.dto;

import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDto {

  private List<UserDto> users;

  private String name;

  private LocalDateTime startDate;

  private int capacity;

  private Course course;

}
