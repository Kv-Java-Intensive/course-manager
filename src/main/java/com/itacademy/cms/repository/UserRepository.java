package com.itacademy.cms.repository;

import com.itacademy.cms.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  @Query("SELECT u FROM User u WHERE u.email = ?1")
  User findByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.firstName = ?1")
  User findByName(String name);

  Optional<User> findByUuid(String uuid);

  boolean existsByUuid(String uuid);

  void deleteByUuid(String uuid);

  @Query(value = "UPDATE User SET active=?2 WHERE id = ?1")
  @Modifying
  void blockUser(String uuid, boolean active);
}