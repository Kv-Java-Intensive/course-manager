package com.itacademy.cms.service;

import com.itacademy.cms.exeption.TagNotFoundException;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.repository.TagRepository;
import com.itacademy.cms.service.impl.TagServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
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

  @Mock
  TagRepository tagRepository;

  @InjectMocks
  TagServiceImpl tagServiceImpl;

  @Test
  void findAllTest() {
    Assertions.assertThrows(TagNotFoundException.class, () -> tagServiceImpl.getAllTags());
    Mockito.verify(tagRepository).findAll();
  }

  @Test
  void findByIdTestExpectedException() {
    Long id = 1L;
    Assertions.assertThrows(TagNotFoundException.class, () -> tagServiceImpl.findTagbyId(id));
    Mockito.verify(tagRepository).findById(id);
  }

  @Test
  void findByIdTestExpectedTag() {
    Long id = 1L;

    Tag tag = new Tag();
    tag.setId(id);
    tag.setName("newName");

    Optional<Tag> optionalTag = Optional.of(tag);
    Mockito.when(tagRepository.findById(id)).thenReturn(optionalTag);
    Tag savedTag = tagServiceImpl.findTagbyId(id);

    Assertions.assertEquals(tag.getId(), savedTag.getId());
    Assertions.assertEquals(tag.getName(), savedTag.getName());
  }

}