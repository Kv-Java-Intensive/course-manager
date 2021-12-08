package com.itacademy.cms.repository;

import com.itacademy.cms.model.Tag;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, UUID> {
  Tag findByName(String name);
}