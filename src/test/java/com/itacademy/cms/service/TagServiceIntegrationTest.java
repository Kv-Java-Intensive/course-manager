package com.itacademy.cms.service;

import com.itacademy.cms.exeption.TagNotFoundException;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import com.itacademy.cms.repository.TagRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Transactional(Transactional.TxType.REQUIRED)
@ActiveProfiles(profiles = "test")
public class TagServiceIntegrationTest {
  public static Long ID = 1L;

  @Autowired
  TagService tagService;

  @Autowired
  TagRepository tagRepository;

  @BeforeEach
  void cleanTable() {
    tagRepository.deleteAll();
  }


  private Tag createAndGetTag() {
    TagDto tagDto = new TagDto();
    tagDto.setName("tagTest");
    tagService.saveTag(tagDto);
    return tagService.findTagbyId(ID++);
  }

  @Test
  void findTagbyIdTest() {
    Tag actualTag = createAndGetTag();
    Tag expectedTag = tagService.findTagbyId(actualTag.getId());
    Assertions.assertEquals(actualTag.getName(), expectedTag.getName());
  }


  @Test
  void updateTagTest() {

    TagDto newTag = new TagDto();
    newTag.setName("newName");
    Tag actualTag = createAndGetTag();
    tagService.updateTag(newTag, actualTag.getId());
    Tag expectedTag = tagService.findTagbyId(actualTag.getId());
    Assertions.assertEquals(newTag.getName(), expectedTag.getName());

  }

  @Test
  void tagDeleteTest() {
    Tag actualTag = createAndGetTag();
    Assertions.assertNotNull(tagService.findTagbyId(actualTag.getId()));
    tagService.deleteTag(actualTag.getId());
    Assertions.assertThrows(TagNotFoundException.class,
        () -> tagService.findTagbyId(actualTag.getId()));

  }

}
