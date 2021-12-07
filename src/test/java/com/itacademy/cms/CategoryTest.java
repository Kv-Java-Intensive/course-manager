package com.itacademy.cms;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.itacademy.cms.mapper.EntityMapper;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class CategoryTest {

  EntityMapper entityMapper = Mappers.getMapper(EntityMapper.class);

  @Test
  public void shouldMapCategoryToDto() {
    Category category = new Category();
    category.setCategoryName("Programming");

    CategoryDto categoryDto = entityMapper.categoryToCategoryDto(category);

    assertEquals(categoryDto.getCategoryName(), category.getCategoryName());
  }

  @Test
  public void shouldMapDtoToCategory() {

    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setCategoryName("Programming");

    Category category = entityMapper.categoryDtoToCategory(categoryDto);
    
    assertThat(category.getCategoryName()).isEqualTo("Programming");

    assertEquals(categoryDto.getCategoryName(), category.getCategoryName());

  }
}