package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import com.itacademy.cms.repository.TagRepository;
import com.itacademy.cms.service.TagService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;

  private final MapStructMapper tagMapper;


  @Override
  public List<Tag> getAllTags() {

    List<Tag> tagList = tagRepository.findAll();
    if (tagList.isEmpty()) {
      throw new EntityNotFoundException("No tags found!");
    }
    return tagList;
  }

  @Override
  public Tag saveTag(TagDto tagDto) {

    return tagRepository.save(tagMapper.tagDtoToTag(tagDto));

  }

  @Override
  public Tag findByUuid(String uuid) {

    Optional<Tag> tag = tagRepository.findByUuid(uuid);
    return tag.orElseThrow(
        () -> new EntityNotFoundException("Tag with iduuid " + uuid + " not found!"));
  }

  @Override
  public void updateTag(TagDto tagDto, String uuid) {
    Optional<Tag> tagOptional = tagRepository.findByUuid(uuid);
    tagOptional.ifPresent(tag -> {
      tag.setName(tagDto.getName());
      tagRepository.save(tag);
    });
  }

  @Override
  public void deleteTagByUuid(String uuid) {
    if (uuid == null) {
      throw new ParameterMissingException("Tag uuid is missing!");
    } else if (tagRepository.existsByUuid(uuid)) {
      tagRepository.deleteByUuid(uuid);
      return;
    }
    throw new EntityNotFoundException("Tag with uuid " + uuid + " not found!");
  }


}
