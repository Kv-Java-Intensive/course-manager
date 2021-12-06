package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


//@Component
@Mapper(componentModel = "spring")
public interface TagMapper {

  TagMapper tagMapper = Mappers.getMapper(TagMapper.class);

  List<TagDto> listTagToListTagDto(List<Tag> tagList);

  TagDto tagToTagDto(Tag tag);

  Tag tagDtoToTag(TagDto tagDto);
}

