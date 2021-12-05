package com.itacademy.cms.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.enums.Language;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class CourseDto {
  private UserDto userDto;

  private String courseName;

  private String description;

  private Double price;

  private CategoryDto categoryDto;

  private String updateDate;

  private Double duration; //hours

  private String language;

  // private List<UserToCourseDto> userToCourseDTo;

  private List<TagDto> tagDto;

  private CertificateDto certificateDto;

  //private GroupDto groupDto;

  //private ModuleDto moduleDto;
}
