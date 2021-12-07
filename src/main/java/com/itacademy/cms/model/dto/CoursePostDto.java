package com.itacademy.cms.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoursePostDto {

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
  private List<TagDto> tags;

  @JsonProperty("certificate")
  private CertificateDto certificateDto;
}
