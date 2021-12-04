package com.itacademy.cms.service;

import com.itacademy.cms.model.Category;
import java.util.List;

public interface CategoryService {

  List<Category> getAllCategories();

  void saveCategory(Category category);

  void deleteCategory(int id);
}