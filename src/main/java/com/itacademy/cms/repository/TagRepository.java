package com.itacademy.cms.repository;

import com.itacademy.cms.model.Tag;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
  Tag findByName(String name);
}