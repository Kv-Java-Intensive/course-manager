package com.itacademy.cms.service;

import com.itacademy.cms.exeption.CategoryNotFoundException;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

  List<Category> findAll() throws CategoryNotFoundException;

  //  Category findById(Long id) throws CategoryNotFoundException;
  Category findByUuid(String uuid) throws CategoryNotFoundException;

  void updateCategory(CategoryDto categoryDto, String uuid) throws CategoryNotFoundException;

  Category saveCategory(CategoryDto categoryDto);

  void deleteCategoryById(Long id) throws CategoryNotFoundException;

//  Category findById(Long id) throws CategoryNotFoundException;

  void deleteCategoryByUuid(String uuid) throws CategoryNotFoundException;
}