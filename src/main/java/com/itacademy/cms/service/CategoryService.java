package com.itacademy.cms.service;

import com.itacademy.cms.exeption.CategoryNotFoundException;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

  List<Category> findAll() throws CategoryNotFoundException;

  Category findById(Long id) throws CategoryNotFoundException;

  void updateCategory(CategoryDto categoryDto, Long id) throws CategoryNotFoundException;

  void saveCategory(CategoryDto categoryDto);

  void deleteCategoryById(Long id) throws CategoryNotFoundException;
}


