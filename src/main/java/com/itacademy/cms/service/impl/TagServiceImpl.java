package com.itacademy.cms.service.impl;

import static com.itacademy.cms.mapper.TagMapper.tagMapper;

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

//  public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
//    this.tagRepository = tagRepository;
//    this.tagMapper = tagMapper;
//  }

  @Override
  public List<Tag> getAllTags() {
    return tagRepository.findAll();
  }

  @Override
  public void saveTag(TagDto tagDto) {
    tagRepository.save(tagMapper.tagDtoToTag(tagDto));
  }

  @Override
  public Tag findTagbyId(long id) {
    return tagRepository.getById(id);
  }

  @Override
  public void updateTag(TagDto tagDto, Long id) {
    Optional<Tag> tagOptional = tagRepository.findById(id);
    if (tagOptional.isPresent()) {
      Tag tag = tagOptional.get();
      tag.setName(tagDto.getName());
      tagRepository.save(tag);
    }
  }

  @Override
  public void deleteTag(long id) {
    tagRepository.deleteById(id);
  }
}
