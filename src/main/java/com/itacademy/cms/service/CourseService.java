package com.itacademy.cms.service;


import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.dto.CoursePostDto;
import java.util.List;

public interface CourseService {

  List<Course> getAllCourses();

  List<Course> getAllCoursesByCategory(String category);

  List<Course> getAllCoursesByTag(String tagName);

  void updateCourse(CoursePostDto coursePostDto, Long id);

  Course getCourseByUuid(String uuid);

  void deleteCourseByUuid(String uuid);

  Course saveCourse(CoursePostDto coursePostDto);
}
