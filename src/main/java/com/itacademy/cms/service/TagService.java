package com.itacademy.cms.service;

import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import java.util.List;

public interface TagService {


  List<Tag> getAllTags();

  Tag saveTag(TagDto tagDto);

  void updateTag(TagDto tagDto, String uuid);

  Tag findByUuid(String uuid);

  void deleteTagByUuid(String uuid);
}

