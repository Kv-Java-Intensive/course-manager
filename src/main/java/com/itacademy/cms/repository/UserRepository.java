package com.itacademy.cms.repository;

import com.itacademy.cms.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByUuid(String uuid);

  boolean existsByUuid(String uuid);

  void deleteByUuid(String uuid);
}
