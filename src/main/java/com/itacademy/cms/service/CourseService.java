package com.itacademy.cms.service;


import com.itacademy.cms.exeption.CourseNotFoundException;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CoursePostDto;
import java.util.List;
import java.util.UUID;

public interface CourseService {

  List<Course> getAllCourses() throws CourseNotFoundException;

  List<Course> getAllCoursesByCategory(String category) throws CourseNotFoundException;

  List<Course> addCourse(CoursePostDto coursePostDto, User user);

  List<Course> getAllCoursesByTag(String tagName) throws CourseNotFoundException;

  Course getCourseById(UUID id) throws CourseNotFoundException;

  void updateCourse(CoursePostDto coursePostDto, UUID id);

  void deleteCourseById(UUID id) throws CourseNotFoundException;
}
