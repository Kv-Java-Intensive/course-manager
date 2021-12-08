package com.itacademy.cms.service;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.repository.TagRepository;
import com.itacademy.cms.service.impl.TagServiceImpl;
import java.util.Optional;
import java.util.UUID;
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
    Assertions.assertThrows(EntityNotFoundException.class, () -> tagServiceImpl.getAllTags());
    Mockito.verify(tagRepository).findAll();
  }

  @Test
  void findByIdTestExpectedException() {
    UUID id = UUID.randomUUID();
    Assertions.assertThrows(EntityNotFoundException.class, () -> tagServiceImpl.findTagbyId(id));
    Mockito.verify(tagRepository).findById(id);
  }

  @Test
  void findByIdTestExpectedTag() {
    UUID id = UUID.randomUUID();

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