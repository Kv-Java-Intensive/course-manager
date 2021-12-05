package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
  CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

  public Course courseDtoToCourse(CourseDto courseDto);

  public CourseDto CourseDtoResponse(Course course);
}
