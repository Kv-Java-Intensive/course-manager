package com.itacademy.cms.repository;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.Tag;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>,
    JpaSpecificationExecutor<Course> {
  List<Course> findCourseByCategory(Category category);

  List<Course> findCourseByCourseTags(Tag tag);

  @NonNull
  List<Course> findAll();

  Course findCourseById(Long id);

}
