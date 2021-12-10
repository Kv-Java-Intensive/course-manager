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
    String uuid = UUID.randomUUID().toString();
    Assertions.assertThrows(EntityNotFoundException.class, () -> tagServiceImpl.findByUuid(uuid));
    Mockito.verify(tagRepository).findByUuid(uuid);
  }

  @Test
  void findByIdTestExpectedTag() {
    String uuid = UUID.randomUUID().toString();

    Tag tag = new Tag();
    tag.setUuid(uuid);
    tag.setName("newName");

    Optional<Tag> optionalTag = Optional.of(tag);
    Mockito.when(tagRepository.findByUuid(uuid)).thenReturn(optionalTag);
    Tag savedTag = tagServiceImpl.findByUuid(uuid);

    Assertions.assertEquals(tag.getId(), savedTag.getId());
    Assertions.assertEquals(tag.getName(), savedTag.getName());
  }

}