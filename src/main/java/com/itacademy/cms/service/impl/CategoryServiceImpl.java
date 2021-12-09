package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.CategoryNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import com.itacademy.cms.repository.CategoryRepository;
import com.itacademy.cms.service.CategoryService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


  private final CategoryRepository categoryRepository;
  private final MapStructMapper categoryMapper;


  @Override
  public List<Category> findAll() throws CategoryNotFoundException {
    List<Category> categoriesList = categoryRepository.findAll();
    if (categoriesList.isEmpty()) {
      throw new CategoryNotFoundException("No categories found!");
    }
    return categoriesList;
  }

  @Override
  public void updateCategory(CategoryDto categoryDto, String uuid)
      throws CategoryNotFoundException {
    Optional<Category> categoryOptional = categoryRepository.findByUuid(uuid);
    categoryOptional.ifPresent(category -> {
      category.setCourses(categoryDto.getCourses());
      category.setCategoryName(categoryDto.getCategoryName());
//      category.setUuid(categoryDto.getUuid());
      categoryRepository.save(category);
    });
  }

  @Override
  public Category findByUuid(String uuid) throws CategoryNotFoundException {
    Optional<Category> category = categoryRepository.findByUuid(uuid);
    if (category.isPresent()) {
      return categoryRepository.getByUuid(uuid);
    }
    throw new CategoryNotFoundException("Category with id " + uuid + " not found!");
  }

  @Override
  public Category saveCategory(CategoryDto categoryDto) {
    categoryRepository.save(categoryMapper.categoryDtoToCategory(categoryDto));
    return null;
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

  @Override
  public Category findById(Long id) {
    return null;
  }

}