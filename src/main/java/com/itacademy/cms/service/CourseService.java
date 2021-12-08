package com.itacademy.cms.service;


import com.itacademy.cms.exeption.CourseNotFoundException;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CoursePostDto;
import java.util.List;

public interface CourseService {

  List<Course> getAllCourses() throws CourseNotFoundException;

  List<Course> getAllCoursesByCategory(String category) throws CourseNotFoundException;

  List<Course> addCourse(CoursePostDto coursePostDto, User user);

  List<Course> getAllCoursesByTag(String tagName) throws CourseNotFoundException;

  Course getCourseById(Long id) throws CourseNotFoundException;

  void updateCourse(CoursePostDto coursePostDto, Long id);

  void deleteCourseById(Long id) throws CourseNotFoundException;
}
