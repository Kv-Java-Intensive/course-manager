package com.itacademy.cms.controller;

import com.itacademy.cms.exeption.CategoryNotFoundException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.CategoryDto;
import com.itacademy.cms.service.CategoryService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;
  private final MapStructMapper entityMapper;

  @GetMapping("/categories")
  public List<CategoryDto> getAllCategories() throws CategoryNotFoundException {
    return categoryService.findAll().stream()
        .map(entityMapper::categoryToCategoryDto).collect(Collectors.toList());
  }

  @GetMapping("/categories/{id}")
  public CategoryDto getCategoryById(@PathVariable("id") UUID id) throws CategoryNotFoundException {
    return entityMapper.categoryToCategoryDto(categoryService.findById(id));
  }

  @PostMapping("/categories")
  public void saveCategory(@RequestBody CategoryDto categoryDto) {
    categoryService.saveCategory(categoryDto);
  }

  @PutMapping("/categories/{id}")
  public void updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable UUID id)
      throws CategoryNotFoundException {
    categoryService.updateCategory(categoryDto, id);
  }

  @DeleteMapping("/categories/{id}")
  public void deleteCategory(@PathVariable("id") UUID id) throws CategoryNotFoundException {
    categoryService.deleteCategoryById(id);
  }
}
