package com.itacademy.cms.service;


import com.itacademy.cms.exeption.CourseNotFoundException;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CoursePostDto;
import java.util.List;

public interface CourseService {

  public List<Course> getAllCourses() throws CourseNotFoundException;

  public List<Course> getAllCoursesByCategory(String category) throws CourseNotFoundException;

  public List<Course> addCourse(CoursePostDto coursePostDto, User user);

  public List<Course> getAllCoursesByTag(String tagName) throws CourseNotFoundException;

  public Course getCourseById(Long id) throws CourseNotFoundException;

  public void updateCourse(CoursePostDto coursePostDto, Long id);

  public void deleteCourseById(Long id) throws CourseNotFoundException;
}
