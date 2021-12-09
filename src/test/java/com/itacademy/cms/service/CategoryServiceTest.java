package com.itacademy.cms.service;

import com.itacademy.cms.exeption.CategoryNotFoundException;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.repository.CategoryRepository;
import com.itacademy.cms.service.impl.CategoryServiceImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

  @Mock
  CategoryRepository categoryRepository;

  @InjectMocks
  CategoryServiceImpl categoryServiceImpl;

  @Test
  void findAllTest() {
    Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryServiceImpl.findAll());
    Mockito.verify(categoryRepository).findAll();
  }

  @Test
  void findByIdTestExpectedException() {
    String uuid = UUID.randomUUID().toString();
    Assertions.assertThrows(CategoryNotFoundException.class,
        () -> categoryServiceImpl.findByUuid(uuid));
    Mockito.verify(categoryRepository).findByUuid(uuid);
  }

  @Test
  void findByUuidTestExpectedCategory() throws CategoryNotFoundException {
    String uuid = UUID.randomUUID().toString();

    Category category = new Category();
    category.setUuid(uuid);
    category.setCategoryName("testName");

    Optional<Category> optionalCategory = Optional.of(category);
    Mockito.when(categoryRepository.findByUuid(uuid)).thenReturn(optionalCategory);
    Category savedCategory = categoryServiceImpl.findByUuid(uuid);

    Assertions.assertEquals(category.getUuid(), savedCategory.getUuid());
    Assertions.assertEquals(category.getCategoryName(), savedCategory.getCategoryName());
  }
}
