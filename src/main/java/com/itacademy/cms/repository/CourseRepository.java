package com.itacademy.cms.repository;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.Tag;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>,
    JpaSpecificationExecutor<Course> {
  List<Course> findCourseByCategory(Category category);

  List<Course> findCourseByCourseTags(Tag tag);

  @NonNull
  List<Course> findAll();

  Optional<Course> findCourseByUuid(String uuid);

  boolean existsByUuid(String uuid);

  void deleteByUuid(String uuid);

  Course findCourseById(Long id);
}
