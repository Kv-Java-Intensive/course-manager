package com.itacademy.cms.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itacademy.cms.model.dto.GroupDto;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.model.UserToCourse;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseGetDto {

  @JsonProperty("courseName")
  private String courseName;

  @JsonProperty("description")
  private String description;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("category")
  private CategoryDto categoryDto;

  @JsonProperty("updateDate")
  private Date updateDate;

  @JsonProperty("duration")
  private Double duration;

  @JsonProperty("language")
  private String language;

  @JsonProperty("tags")
  private List<TagGetDto> tags;

  @JsonProperty("certificate")
  private CertificateDto certificateDto;

  @JsonProperty("users")
  private List<UserToCourseDto> users;

  @JsonProperty("groups")
  private List<GroupDto> groups;

  @JsonProperty("modules")
  private List<ModuleDto> modules;
}

