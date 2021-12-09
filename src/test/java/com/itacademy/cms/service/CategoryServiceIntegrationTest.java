package com.itacademy.cms.service;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import com.itacademy.cms.repository.CategoryRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Transactional(Transactional.TxType.REQUIRED)
@ActiveProfiles(profiles = "test")
public class CategoryServiceIntegrationTest {

  @Autowired
  CategoryService categoryService;

  @Autowired
  CategoryRepository categoryRepository;

  @BeforeEach
  void cleanTable() {
    categoryRepository.deleteAll();
  }

  @Test
  void categorySaveAndFindByIdTest() {
    Category savedCategory = getSavedCategory();

    Category categoryById = categoryService.findById(savedCategory.getId());

    Assertions.assertEquals(savedCategory.getCategoryName(), categoryById.getCategoryName());
  }

  @Test
  void categoryUpdateTest() {
    CategoryDto categoryDtoUpdate = new CategoryDto();

    categoryDtoUpdate.setCategoryName("testName");

    Category initCategory = getSavedCategory();

    categoryService.updateCategory(categoryDtoUpdate, initCategory.getId());

    Category updateCategory = categoryService.findById(initCategory.getId());

    Assertions.assertEquals(categoryDtoUpdate.getCategoryName(), updateCategory.getCategoryName());
  }

  @Test
  void categoryDeleteTest() {
    Category initCategory = getSavedCategory();

    Assertions.assertNotNull(categoryService.findById(initCategory.getId()));

    categoryService.deleteCategoryById(initCategory.getId());

    Assertions.assertThrows(EntityNotFoundException.class,
        () -> categoryService.findById(initCategory.getId()));
  }

  private Category getSavedCategory() {
    CategoryDto categoryDto = new CategoryDto();

    categoryDto.setCategoryName("testName");

    return categoryService.saveCategory(categoryDto);
  }
}

