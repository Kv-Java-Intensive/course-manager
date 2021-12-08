package com.itacademy.cms.repository;

import com.itacademy.cms.model.Category;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, UUID> {
  Category findByCategoryName(String categoryName);

  Category getById(UUID id);
}