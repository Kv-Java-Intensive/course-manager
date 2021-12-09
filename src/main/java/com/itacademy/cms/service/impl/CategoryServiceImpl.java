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
  private final MapStructMapper entityMapper;


  @Override
  public List<Category> findAll() {
    List<Category> categoriesList = (List<Category>) categoryRepository.findAll();
    if (categoriesList.isEmpty()) {
      throw new EntityNotFoundException("No categories found!");
    }
    return categoriesList;
  }

//  @Override
//  public void updateCategory(CategoryDto categoryDto, Long id) {
//    Optional<Category> categoryOptional = categoryRepository.findById(id);
//    categoryOptional.ifPresent(category -> {
//      category.setCourses(categoryDto.getCourses());
//      category.setCategoryName(categoryDto.getCategoryName());
//      categoryRepository.save(category);
//    });
//  }

  @Override
  public Category findById(Long id) {
    Optional<Category> category = categoryRepository.findById(id);
    return category.orElseThrow(
        () -> new EntityNotFoundException("Category with id " + id + " not found!"));
  }

  @Override
  public Category saveCategory(CategoryDto categoryDto) {
    return categoryRepository.save(entityMapper.categoryDtoToCategory(categoryDto));
  }

  @Override
  public void deleteCategoryById(Long id) {
    if (id == null) {
      throw new ParameterMissingException("Category id is missing");
    } else if (categoryRepository.existsById(id)) {
      categoryRepository.deleteById(id);
      return;
    }
    throw new EntityNotFoundException("Category with id " + id + " not found!");
  }

}
