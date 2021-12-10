package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
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
  public List<Category> findAll() {
    List<Category> categoriesList = categoryRepository.findAll();
    if (categoriesList.isEmpty()) {
      throw new EntityNotFoundException("No categories found!");
    }
    return categoriesList;
  }

  @Override
  public void updateCategory(CategoryDto categoryDto, String uuid) {
    Optional<Category> categoryOptional = categoryRepository.findByUuid(uuid);
    categoryOptional.ifPresent(category -> {
      category.setCourses(categoryDto.getCourses());
      category.setCategoryName(categoryDto.getCategoryName());
      categoryRepository.save(category);
    });
  }

  @Override
  public Category findByUuid(String uuid) {
    Optional<Category> category = categoryRepository.findByUuid(uuid);
    return category.orElseThrow(
        () -> new EntityNotFoundException("Category with uuid " + uuid + " not found!"));
  }

  @Override
  public Category saveCategory(CategoryDto categoryDto) {
    return categoryRepository.save(categoryMapper.categoryDtoToCategory(categoryDto));
  }

  @Override
  public void deleteCategoryByUuid(String uuid) {
    if (uuid == null) {
      throw new ParameterMissingException("Category uuid is missing");
    } else if (categoryRepository.existsByUuid(uuid)) {
      categoryRepository.deleteByUuid(uuid);
      return;
    }
    throw new EntityNotFoundException("Category with uuid " + uuid + " not found!");
  }

}