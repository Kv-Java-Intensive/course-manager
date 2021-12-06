package com.itacademy.cms.service;

import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional
public class TagServiceDbTest {
  public static int ID = 1;

  @Autowired
  TagService tagService;
  @Autowired
  EntityManager entityManager;

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

    entityManager.flush();
    entityManager.clear();

    Tag expectedTag = tagService.findTagbyId(actualTag.getId());
    Assertions.assertEquals(newTag.getName(), expectedTag.getName());

  }

  @Test
  void tagDeleteTest() {
    Tag actualTag = createAndGetTag();
    Assertions.assertNotNull(tagService.findTagbyId(actualTag.getId()));
    tagService.deleteTag(actualTag.getId());

  }

}
