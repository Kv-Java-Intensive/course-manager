package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.CategoryDto;
import com.itacademy.cms.service.CategoryService;
import java.util.List;
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
  public List<CategoryDto> getAllCategories(){
    return categoryService.findAll().stream()
        .map(entityMapper::categoryToCategoryDto).collect(Collectors.toList());
  }

  @GetMapping("/categories/{id}")
  public CategoryDto getCategoryById(@PathVariable("id") Long id)  {
    return entityMapper.categoryToCategoryDto(categoryService.findById(id));
  }

  @PostMapping("/categories")
  public void saveCategory(@RequestBody CategoryDto categoryDto) {
    categoryService.saveCategory(categoryDto);
  }

  @DeleteMapping("/categories/{id}")
  public void deleteCategory(@PathVariable("id") Long id) {
    categoryService.deleteCategoryById(id);
  }
}
