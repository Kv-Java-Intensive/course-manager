package com.itacademy.cms.service;

import com.itacademy.cms.model.dto.CourseDto;
import com.itacademy.cms.model.Course;
import java.util.List;

public interface CourseService {

  public List<Course> getAllCourses();

  public List<Course> getAllCoursesByCategory(String category);

  public List<Course> addCourse(CourseDto courseDto);

  public List<Course> getAllCoursesByTag(String tagName);

  public Course getCourseById(Long id);

  public void updateCourse(CourseDto courseDto, Long id);

  public void deleteCourseById(Long id);
}
