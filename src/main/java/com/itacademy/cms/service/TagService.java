package com.itacademy.cms.service;

import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import java.util.List;
import java.util.UUID;

public interface TagService {


  List<Tag> getAllTags();

  Tag saveTag(TagDto tagDto);

  Tag findTagbyId(UUID id);

  void updateTag(TagDto tagDto, UUID id);

  void deleteTag(UUID id);


}

