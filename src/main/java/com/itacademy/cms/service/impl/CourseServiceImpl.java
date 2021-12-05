package com.itacademy.cms.service.impl;

import com.itacademy.cms.dao.CourseDAO;
import com.itacademy.cms.model.dto.CourseDto;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
  @Autowired
  private CourseDAO courseDAO;
  @Autowired
  private CategoryDAO categoryDAO;
  @Autowired
  private TagDAO tagDAO;
  @Autowired
  private CertificateDAO certificateDAO;

  @Override
  public List<Course> getAllCourses() {
    return courseDAO.findAll();
  }

  @Override
  public List<Course> getAllCoursesByCategory(String categoryName) {
    return courseDAO.findCourseByCategory(categoryDAO.findByCategory());
  }

  @Override
  public List<Course> addCourse(CourseDto courseDto) {
    Course course = new Course();
    course.setCourseName(courseDto.getCourseName());
    course.setDescription(courseDto.getDescription());
    course.setPrice(courseDto.getPrice());
    Category category = categoryDAO.findByCategory(courseDto.getCategoryDto().getCategory());
    course.setCategory(category);
    course.setUpdateDate(courseDto.getUpdateDate()); //time
    course.setDuration(courseDto.getDuration());
    course.setLanguage(courseDto.getLanguage());
    //course.setCourseTags(courseDto.getCourseTags);
    Certificate certificate = certificateDAO.findByName(courseDto.getCertificateDto().getName());
    course.setCertificate(certificate);
    courseDAO.save(course);
    return courseDAO.findAll();
  }

  @Override
  public List<Course> getAllCoursesByTag(String tagName) {
    return courseDAO.findCourseByCategory(tagDAO.findByName());
  }

  @Override
  public Course getCourseById(Long id) {
    return courseDAO.findCourseById(id);
  }

  @Override
  public void updateCourse(CourseDto courseDto, Long id) {
    Course course = courseDAO.findCourseById(id);
    course.setCourseName(courseDto.getCourseName());
    course.setDescription(courseDto.getDescription());
    course.setPrice(courseDto.getPrice());
    Category category = categoryDAO.findByCategory(courseDto.getCategoryDto().getCategory());
    course.setCategory(category);
    course.setUpdateDate(courseDto.getUpdateDate()); //time
    course.setDuration(courseDto.getDuration());
    course.setLanguage(courseDto.getLanguage());
    //course.setCourseTags(courseDto.getCourseTags);
    Certificate certificate = certificateDAO.findByName(courseDto.getCertificateDto().getName());
    course.setCertificate(certificate);
    courseDAO.save(course);
  }

  @Override
  public void deleteCourseById(Long id) {
    courseDAO.deleteById(id);
  }
}
