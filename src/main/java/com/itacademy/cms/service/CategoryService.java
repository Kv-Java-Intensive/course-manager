package com.itacademy.cms.service;


import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

  List<Category> findAll();

  Category findByUuid(String uuid);

  void updateCategory(CategoryDto categoryDto, String uuid);

  Category saveCategory(CategoryDto categoryDto);

  void deleteCategoryByUuid(String uuid);
}
