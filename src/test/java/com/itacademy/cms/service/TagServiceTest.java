package com.itacademy.cms.service;

import com.itacademy.cms.exeption.TagNotFoundException;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.repository.TagRepository;
import com.itacademy.cms.service.impl.TagServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TagServiceTest {

  static List<Tag> tagList;

  @Mock
  TagRepository tagRepository;

  @InjectMocks
  TagServiceImpl tagServiceImpl;


  @BeforeAll
  static void createTags() {
    tagList = new ArrayList<>();
    for (int i = 1; i < 5; i++) {
      Tag tag = new Tag();
      tag.setName(1 + " tagExample");
      tagList.add(tag);
    }
  }

  @Test
  void findAllTagsTest() {
    Mockito.when(tagRepository.findAll()).thenReturn(tagList);
  }

  @Test
  void findTagByIdExpectedExceptionTest() {
    Long id = 1L;
    Assertions.assertThrows(TagNotFoundException.class, () -> tagServiceImpl.findTagbyId(id));
    Mockito.verify(tagRepository).findById(id);
  }

  @Test
  void findTagByIdTest() {
    Long id = 2L;
    Optional<Tag> optionalTag = Optional.of(tagList.get(id.intValue()));
    Mockito.when(tagRepository.findById(id)).thenReturn(optionalTag);
  }


}