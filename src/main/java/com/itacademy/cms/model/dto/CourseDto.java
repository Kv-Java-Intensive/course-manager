package com.itacademy.cms.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itacademy.cms.model.enums.Language;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class CourseDto {

  private String courseName;

  private String description;

  private Double price;

  private CategoryDto categoryDto;

  private Date updateDate;

  private Double duration; //hours

  private Language language;

  //private UserToCourseDto userToCourseDTo;

  private List<TagDto> tagDto;

  private CertificateDto certificateDto;

  //private GroupDto groupDto;

  //private ModuleDto moduleDto;
}
