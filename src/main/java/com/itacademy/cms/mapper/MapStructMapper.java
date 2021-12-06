package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.dto.CourseGetDto;
import com.itacademy.cms.model.dto.CoursePostDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
  //MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

  Course courseDtoToCourse(CoursePostDto coursePostDto);

  CourseGetDto courseToCourseGetDto(Course course);

  List<CourseGetDto> courseAllToCourseGetDto(List<Course> courseList);


}
