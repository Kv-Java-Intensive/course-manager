package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface TagMapper {

  TagMapper tagMapper = Mappers.getMapper(TagMapper.class);

  TagDto tagToTagDto(Tag tag);

  Tag tagDtoToTag(TagDto tagDto);
}

