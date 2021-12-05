package com.itacademy.cms.repository;

import com.itacademy.cms.model.Group;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group, Long> {

  @Query(value = "select group_id from users_groups where user_id = :id", nativeQuery = true)
  List<Group> getAllByUser(@Param("id") Long id);
}
