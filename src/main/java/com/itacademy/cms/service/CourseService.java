package com.itacademy.cms.service;


import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CoursePostDto;
import java.util.List;

public interface CourseService {

  List<Course> getAllCourses();

  List<Course> addCourse(CoursePostDto coursePostDto, User user);

  List<Course> getAllCoursesByTag(String tagName);

  Course getCourseById(Long id);

  void updateCourse(CoursePostDto coursePostDto, Long id);

  void deleteCourseById(Long id);

  List<Course> getAllCoursesByCategory(String categoryName);
}
