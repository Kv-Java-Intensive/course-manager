package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

  Category categoryDtoToCategory(CategoryDto categoryDto);

  CategoryDto categoryToCategoryDto(Category category);


}
