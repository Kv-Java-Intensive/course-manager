package com.itacademy.cms.repository;

import com.itacademy.cms.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
  Category findByCategoryName(String categoryName);

  Category getById(Long id);
}