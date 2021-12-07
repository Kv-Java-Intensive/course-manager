package com.itacademy.cms.repository;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.Tag;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDAO extends CrudRepository<Course, Long> {
  public List<Course> findCourseByCategory(Category category);

  public List<Course> findCourseByCourseTags(Tag tag);

  public List<Course> findAll();

  public Course findCourseById(Long id);

}
