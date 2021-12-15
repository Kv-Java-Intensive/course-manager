package com.itacademy.cms.repository;

import com.itacademy.cms.model.User;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {
  Optional<User> findByUuid(String uuid);

  boolean existsByUuid(String uuid);

  void deleteByUuid(String uuid);

  @Query(value = "UPDATE User SET active=?1 WHERE uuid = ?2")
  @Modifying
  @Transactional
  void blockUser(boolean active, String uuid);
}
