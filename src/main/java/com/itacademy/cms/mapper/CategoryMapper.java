package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);


    CategoryDto categoryToDTO(Category category);


    Category dtoToCategory(CategoryDto categoryDTO);
}
