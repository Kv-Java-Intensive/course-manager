package com.itacademy.cms.service;


import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

  List<Category> findAll();

  Category findById(Long id);

  Category saveCategory(CategoryDto categoryDto);

  void deleteCategoryById(Long id);

  void updateCategory(CategoryDto categoryDto, Long id);
}
