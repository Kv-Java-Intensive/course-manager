package com.itacademy.cms.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.enums.CourseStatus;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class UserToCourseDto {
  private User user;
  private Course course;
  private CourseStatus courseStatus;
  private boolean isAuthor;
}
