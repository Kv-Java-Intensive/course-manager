package com.itacademy.cms.repository;

import com.itacademy.cms.model.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByUuid(String uuid);

  Category getByUuid(String uuid);

  Category findByCategoryName(String categoryName);

  boolean existsByUuid(String uuid);

  void deleteByUuid(String uuid);
}