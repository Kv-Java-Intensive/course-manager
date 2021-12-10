package com.itacademy.cms.repository;

import com.itacademy.cms.model.UserToCourse;
import org.springframework.data.repository.CrudRepository;

public interface UserToCourseRepository extends CrudRepository<UserToCourse,
    UserToCourse.UserToCourseId> {
}
