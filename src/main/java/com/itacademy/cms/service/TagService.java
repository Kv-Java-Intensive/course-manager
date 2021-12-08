package com.itacademy.cms.service;

import com.itacademy.cms.exeption.TagNotFoundException;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import java.util.List;

public interface TagService {


  List<Tag> getAllTags() throws TagNotFoundException;

  void saveTag(TagDto tagDto);

  Tag findTagbyId(Long id) throws TagNotFoundException;

  void updateTag(TagDto tagDto, Long id);

  void deleteTag(Long id) throws TagNotFoundException;


}

