package com.itacademy.cms.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificateDto {
  private String name;
  private CourseGetDto course;
  private UserDto user;
}