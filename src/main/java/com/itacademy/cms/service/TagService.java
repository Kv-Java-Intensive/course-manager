package com.itacademy.cms.service;

import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import java.util.List;

public interface TagService {

  List<Tag> getAllTags();

  void saveTag(TagDto tagDto);

  Tag findTagbyId(long id);

  void updateTag(TagDto tagDto, Long id);

  void deleteTag(long id);


}
