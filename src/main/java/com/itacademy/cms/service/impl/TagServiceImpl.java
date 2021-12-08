package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.exeption.TagNotFoundException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import com.itacademy.cms.repository.TagRepository;
import com.itacademy.cms.service.TagService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;
  private final MapStructMapper tagMapper;


  @Override
  public List<Tag> getAllTags() throws TagNotFoundException {
    List<Tag> tagList = tagRepository.findAll();
    if (tagList.isEmpty()) {
      throw new TagNotFoundException("No tags found!");
    }
    return tagList;
  }

  @Override
  public void saveTag(TagDto tagDto) {
    tagRepository.save(tagMapper.tagDtoToTag(tagDto));
  }


  @Override
  public Tag findTagbyId(UUID id) throws TagNotFoundException {
    Optional<Tag> tag = tagRepository.findById(id);
    return tag.orElseThrow(() -> new TagNotFoundException("Tag with id " + id + " not found!"));
  }

  @Override
  public void updateTag(TagDto tagDto, UUID id) {
    Optional<Tag> tagOptional = tagRepository.findById(id);
    tagOptional.ifPresent(tag -> {
      tag.setName(tagDto.getName());
      tagRepository.save(tag);
    });
  }

  @Override
  public void deleteTag(UUID id) throws TagNotFoundException {
    if (id == null) {
      throw new ParameterMissingException("Tag id is missing!");
    } else if (tagRepository.existsById(id)) {
      tagRepository.deleteById(id);
      return;
    }
    throw new TagNotFoundException("Tag with id " + id + " not found!");
  }


}
