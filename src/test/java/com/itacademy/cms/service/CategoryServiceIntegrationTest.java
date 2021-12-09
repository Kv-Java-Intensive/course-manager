package com.itacademy.cms.service;

import com.itacademy.cms.exeption.CategoryNotFoundException;
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
  void categorySaveAndFindByUuidTest() throws CategoryNotFoundException {
    Category savedCategory = getSavedCategory();

    Category categoryByUuid = categoryService.findByUuid(savedCategory.getUuid());

    Assertions.assertEquals(savedCategory.getCategoryName(), categoryByUuid.getCategoryName());
  }

  @Test
  void categoryUpdateTest() throws CategoryNotFoundException {
    CategoryDto categoryDtoUpdate = new CategoryDto();

    categoryDtoUpdate.setCategoryName("testName");

    Category initCategory = getSavedCategory();

    categoryService.updateCategory(categoryDtoUpdate, initCategory.getUuid());

    Category updateCategory = categoryService.findByUuid(initCategory.getUuid());

    Assertions.assertEquals(categoryDtoUpdate.getCategoryName(), updateCategory.getCategoryName());
  }

  @Test
  void categoryDeleteTest() throws CategoryNotFoundException {
    Category initCategory = getSavedCategory();

    Assertions.assertNotNull(categoryService.findByUuid(initCategory.getUuid()));

    categoryService.deleteCategoryByUuid(initCategory.getUuid());

    Assertions.assertThrows(CategoryNotFoundException.class,
        () -> categoryService.findByUuid(initCategory.getUuid()));
  }

  private Category getSavedCategory() {
    CategoryDto categoryDto = new CategoryDto();

    categoryDto.setCategoryName("testName");

    return categoryService.saveCategory(categoryDto);
  }
}

