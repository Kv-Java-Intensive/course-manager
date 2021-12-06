package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.CategoryNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
//import com.itacademy.cms.mapper.EntityMapper;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import com.itacademy.cms.repository.CategoryDAO;
//import com.itacademy.cms.repository.CategoryRepository;
import com.itacademy.cms.service.CategoryService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


  private final CategoryDAO categoryRepository;
  private final MapStructMapper entityMapper;


  @Override
  public List<Category> findAll() throws CategoryNotFoundException {
    List<Category> categoriesList = categoryRepository.findAll();
    if (categoriesList.isEmpty()) {
      throw new CategoryNotFoundException("No categories found!");
    }
    return categoriesList;
  }

  @Override
  public void updateCategory(CategoryDto categoryDto, Long id) {
    Optional<Category> categoryOptional = categoryRepository.findById(id);
    categoryOptional.ifPresent(category -> {
      category.setCourses(categoryDto.getCourses());
      category.setCategory(categoryDto.getCategoryName());
      categoryRepository.save(category);
    });
  }

  @Override
  public Category findById(Long id) throws CategoryNotFoundException {
    Optional<Category> category = categoryRepository.findById(id);
    if (category.isPresent()) {
      return categoryRepository.getById(id);
    }
    throw new CategoryNotFoundException("Category with id " + id + " not found!");
  }

  @Override
  public void saveCategory(CategoryDto categoryDto) {
    categoryRepository.save(entityMapper.categoryDtoToCategory(categoryDto));
  }

  @Override
  public void deleteCategoryById(Long id) throws CategoryNotFoundException {
    if (id == null) {
      throw new ParameterMissingException("Category id is missing");
    } else if (categoryRepository.existsById(id)) {
      categoryRepository.deleteById(id);
    }
    throw new CategoryNotFoundException("Category with id " + id + " not found!");
  }

}
