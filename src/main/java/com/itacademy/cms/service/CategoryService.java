package com.itacademy.cms.service;

import com.itacademy.cms.exeption.CategoryNotFoundException;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import java.util.List;
import java.util.UUID;

public interface CategoryService {

  List<Category> findAll() throws CategoryNotFoundException;

  Category findById(UUID id) throws CategoryNotFoundException;

  void updateCategory(CategoryDto categoryDto, UUID id) throws CategoryNotFoundException;

  Category saveCategory(CategoryDto categoryDto);

  void deleteCategoryById(UUID id) throws CategoryNotFoundException;
}
