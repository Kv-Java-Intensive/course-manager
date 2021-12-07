package com.itacademy.cms.repository;

import com.itacademy.cms.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDAO extends JpaRepository<Tag, Long> {
  Tag findByName();
}