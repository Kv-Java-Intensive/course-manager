package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.exeption.TagNotFoundException;
import com.itacademy.cms.mapper.TagMapper;
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
  private final TagMapper tagMapper;


  @Override
  public List<Tag> getAllTags() {
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
  public Tag findTagbyId(Long id) {
    Optional<Tag> tag = tagRepository.findById(id);
    return tag.orElseThrow(() -> new TagNotFoundException("Tag with id " + id + " not found!"));
  }

  @Override
  public void updateTag(TagDto tagDto, Long id) {
    Optional<Tag> tagOptional = tagRepository.findById(id);
    tagOptional.ifPresent(tag -> {
      tag.setName(tagDto.getName());
      tagRepository.save(tag);
    });
  }

  @Override
  public void deleteTag(Long id) {
    if (id == null) {
      throw new ParameterMissingException("Tag id is missing!");
    } else if (tagRepository.existsById(id)) {
      tagRepository.deleteById(id);
      return;
    }
    throw new TagNotFoundException("Tag with id " + id + " not found!");
  }


}
