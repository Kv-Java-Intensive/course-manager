package com.itacademy.cms.service;

import com.itacademy.cms.model.dto.CourseDto;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.dto.CoursePostDto;
import java.util.List;

public interface CourseService {

  public List<Course> getAllCourses();

  public List<Course> getAllCoursesByCategory(String category);

  public List<Course> addCourse(CoursePostDto coursePostDto);

  public List<Course> getAllCoursesByTag(String tagName);

  public Course getCourseById(Long id);

  public void updateCourse(CoursePostDto coursePostDto, Long id);

  public void deleteCourseById(Long id);
}
