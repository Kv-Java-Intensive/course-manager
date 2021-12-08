package com.itacademy.cms.service;


import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CoursePostDto;
import java.util.List;
import java.util.UUID;

public interface CourseService {

  List<Course> getAllCourses();

  List<Course> getAllCoursesByCategory(String category);

  List<Course> addCourse(CoursePostDto coursePostDto, User user);

  List<Course> getAllCoursesByTag(String tagName);

  Course getCourseById(UUID id);

  void updateCourse(CoursePostDto coursePostDto, UUID id);

  void deleteCourseById(UUID id);
}
