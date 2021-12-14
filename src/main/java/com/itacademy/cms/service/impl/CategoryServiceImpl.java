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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

  private final CategoryRepository categoryRepository;
  private final MapStructMapper categoryMapper;

  @Override
  public List<Category> findAll() {
    logger.info("GET ALL CATEGORIES");
    List<Category> categoriesList = categoryRepository.findAll();
    if (categoriesList.isEmpty()) {
      logger.error("NO CATEGORIES FOUND");
      throw new EntityNotFoundException("No categories found!");
    }
    return categoriesList;
  }

  @Override
  public void updateCategory(CategoryDto categoryDto, String uuid) {
    logger.info("UPDATE CATEGORY WITH ID = {}", uuid);
    Optional<Category> categoryOptional = categoryRepository.findByUuid(uuid);
    categoryOptional.ifPresent(category -> {
      category.setCategoryName(categoryDto.getCategoryName());
      categoryRepository.save(category);
    });
  }

  @Override
  public Category findByUuid(String uuid) {
    logger.info("GET CATEGORY WITH ID = {}", uuid);
    Optional<Category> category = categoryRepository.findByUuid(uuid);
    if (category.isPresent()) {
      return category.get();
    } else {
      logger.error("CATEGORY WITH ID = {} DOES NOT EXIST", uuid);
      throw new EntityNotFoundException("Category with uuid " + uuid + " not found!");
    }
  }

  @Override
  public Category saveCategory(CategoryDto categoryDto) {
    logger.info("CREATE NEW CATEGORY");
    return categoryRepository.save(categoryMapper.categoryDtoToCategory(categoryDto));
  }

  @Override
  public void deleteCategoryByUuid(String uuid) {
    if (uuid == null) {
      logger.error("UUID IS EMPTY");
      throw new ParameterMissingException("Category uuid is missing");
    } else if (categoryRepository.existsByUuid(uuid)) {
      logger.info("REMOVE CATEGORY WITH ID = {}", uuid);
      categoryRepository.deleteByUuid(uuid);
      return;
    }
    logger.error("CATEGORY WITH ID = {} DOES NOT EXIST", uuid);
    throw new EntityNotFoundException("Category with uuid " + uuid + " not found!");
  }

}
