//package com.itacademy.cms.service;
//
//import com.itacademy.cms.exeption.CategoryNotFoundException;
//import com.itacademy.cms.model.Category;
//import com.itacademy.cms.repository.CategoryRepository;
//import com.itacademy.cms.service.impl.CategoryServiceImpl;
//import java.util.Optional;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class CategoryServiceTest {
//
//  @Mock
//  CategoryRepository categoryRepository;
//
//  @InjectMocks
//  CategoryServiceImpl categoryServiceImpl;
//
//  @Test
//  void findAllTest() {
//    Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryServiceImpl.findAll());
//    Mockito.verify(categoryRepository).findAll();
//  }
//
//  @Test
//  void findByIdTestExpectedException() {
//    Long id = 1L;
//    Assertions.assertThrows(CategoryNotFoundException.class,
//        () -> categoryServiceImpl.findById(id));
//    Mockito.verify(categoryRepository).findById(id);
//  }
//
//  @Test
//  void findByIdTestExpectedCategory() {
//    Long id = 1L;
//
//    Category category = new Category();
//    category.setId(id);
//    category.setCategoryName("testName");
//
//    Optional<Category> optionalCategory = Optional.of(category);
//    Mockito.when(categoryRepository.findById(id)).thenReturn(optionalCategory);
//    Category savedCategory = categoryServiceImpl.findById(id);
//
//    Assertions.assertEquals(category.getId(), savedCategory.getId());
//    Assertions.assertEquals(category.getCategoryName(), savedCategory.getCategoryName());
//  }
//}
