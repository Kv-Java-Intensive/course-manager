package com.itacademy.cms.service;

import com.itacademy.cms.exeption.TagNotFoundException;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import java.util.List;
import java.util.UUID;

public interface TagService {


  List<Tag> getAllTags() throws TagNotFoundException;

  Tag saveTag(TagDto tagDto);

  Tag findTagbyId(UUID id) throws TagNotFoundException;

  void updateTag(TagDto tagDto, UUID id);

  void deleteTag(UUID id) throws TagNotFoundException;


}

