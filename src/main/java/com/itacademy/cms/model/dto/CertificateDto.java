package com.itacademy.cms.model.dto;

import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificateDto {
  private String name;
  private Course course;
  private User user;
}