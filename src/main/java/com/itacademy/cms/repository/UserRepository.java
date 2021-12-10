package com.itacademy.cms.repository;

import com.itacademy.cms.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
  User getUserByEmail(String email);

}
