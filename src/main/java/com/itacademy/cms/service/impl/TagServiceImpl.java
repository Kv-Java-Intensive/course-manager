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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

  private final TagRepository tagRepository;
  private final MapStructMapper tagMapper;

  @Override
  public List<Tag> getAllTags() {
    logger.info("GET ALL TAGS");
    List<Tag> tagList = tagRepository.findAll();
    if (tagList.isEmpty()) {
      logger.error("TAGS LIST IS EMPTY");
      throw new EntityNotFoundException("No tags found!");
    }
    return tagList;
  }

  @Override
  public Tag saveTag(TagDto tagDto) {
    logger.info("CREATE NEW TAG");
    return tagRepository.save(tagMapper.tagDtoToTag(tagDto));
  }

  @Override
  public Tag findByUuid(String uuid) {
    logger.info("GET TAG WITH ID = {}", uuid);
    Optional<Tag> tag = tagRepository.findByUuid(uuid);
    if (tag.isPresent()) {
      return tag.get();
    } else {
      logger.error("TAG WITH ID = {} DOES NOT EXIST", uuid);
      throw new EntityNotFoundException("Tag with iduuid " + uuid + " not found!");
    }
  }

  @Override
  public void updateTag(TagDto tagDto, String uuid) {
    logger.info("UPDATE TAG WITH ID = {}", uuid);
    Optional<Tag> tagOptional = tagRepository.findByUuid(uuid);
    tagOptional.ifPresent(tag -> {
      tag.setName(tagDto.getName());
      tagRepository.save(tag);
    });
  }

  @Override
  public void deleteTagByUuid(String uuid) {
    if (uuid == null) {
      logger.error("UUID IS EMPTY");
      throw new ParameterMissingException("Tag uuid is missing!");
    } else if (tagRepository.existsByUuid(uuid)) {
      logger.info("REMOVE TAG WITH ID = {}", uuid);
      tagRepository.deleteByUuid(uuid);
      return;
    }
    logger.error("TAG WITH ID = {} DOES NOT EXIST", uuid);
    throw new EntityNotFoundException("Tag with uuid " + uuid + " not found!");
  }


}
