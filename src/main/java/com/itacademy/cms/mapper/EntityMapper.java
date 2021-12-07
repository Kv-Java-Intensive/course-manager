package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface EntityMapper {

  EntityMapper ENTITY_MAPPER = Mappers.getMapper(EntityMapper.class);

  TagDto tagToTagDto(Tag tag);

  Tag tagDtoToTag(TagDto tagDto);
}

