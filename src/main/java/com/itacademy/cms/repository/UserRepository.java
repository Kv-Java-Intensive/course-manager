package com.itacademy.cms.repository;

import com.itacademy.cms.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  @Query(value = "SELECT * FROM users WHERE first_name = ?1", nativeQuery = true)
  User getUserByFirstName(String name);

  @Query(value = "SELECT * FROM users WHERE last_name = ?1", nativeQuery = true)
  User getUserByLastName(String surname);

  @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
  User getUserByEmail(String email);
}
