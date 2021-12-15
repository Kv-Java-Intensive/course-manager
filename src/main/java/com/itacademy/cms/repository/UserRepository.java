package com.itacademy.cms.repository;

import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // @Query(value = "select * from users u where u.email = ?1", nativeQuery = true)
  //User findByEmail(String email);
  User findUserByEmail(String email);
}