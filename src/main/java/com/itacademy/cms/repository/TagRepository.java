package com.itacademy.cms.repository;

import com.itacademy.cms.model.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

  Tag findByName(String name);

  Optional<Tag> findByUuid(String uuid);

  boolean existsByUuid(String uuid);

  void deleteByUuid(String uuid);
}

