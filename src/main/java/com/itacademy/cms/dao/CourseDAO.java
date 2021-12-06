package com.itacademy.cms.dao;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.Tag;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CourseDAO extends CrudRepository<Course, Long> {
  public List<Course> findCourseByCategory(Category category);

  public List<Course> findCourseByCourseTags(Tag tag);

  public List<Course> findAll();

  public Course findCourseById(Long id);

}
