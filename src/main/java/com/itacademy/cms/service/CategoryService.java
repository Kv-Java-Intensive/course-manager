package com.itacademy.cms.service;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import java.util.List;
import java.util.UUID;

public interface CategoryService {

  List<Category> findAll();

  Category findById(UUID id);

  void updateCategory(CategoryDto categoryDto, UUID id);

  Category saveCategory(CategoryDto categoryDto);

  void deleteCategoryById(UUID id);
}
