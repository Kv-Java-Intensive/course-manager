package com.itacademy.cms.service.impl;

import com.itacademy.cms.jpaRepository.CategoryRepository;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


  private final CategoryRepository categoryRepository;


  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public void saveCategory(Category category) {
    categoryRepository.save(category);
  }

  @Override
  public void deleteCategory(int id) {
    categoryRepository.deleteById(id);
  }
}