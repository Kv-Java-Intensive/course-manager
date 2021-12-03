package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.TagMapper;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.dto.TagDto;
import com.itacademy.cms.service.impl.TagServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RequiredArgsConstructor
@RestController
public class TagController {


  private final TagServiceImpl tagServiceImpl;
  private final TagMapper tagMapper;

  public TagController(TagServiceImpl tagServiceImpl, TagMapper tagMapper) {
    this.tagServiceImpl = tagServiceImpl;
    this.tagMapper = tagMapper;
  }


  @GetMapping("/tags")
  public List<TagDto> showAllTags() {
    List<TagDto> tagsDto = tagMapper.listTagToListTagDto(tagServiceImpl.getAllTags());
    return tagsDto;
  }

  @GetMapping("/tags/{id}")
  public TagDto getTagById(@PathVariable("id") Long tagId) {
    return tagMapper.tagToTagDto(tagServiceImpl.findTagbyId(tagId));
  }

  @PostMapping("/tags")
  public void saveTag(@RequestBody TagDto tagDto) {
    tagServiceImpl.saveTag(tagDto);
  }

  @PutMapping("/tags/{id}")
  public void updateUser(@RequestBody TagDto tagDto, @PathVariable Long id) {
    tagServiceImpl.updateTag(tagDto, id);
  }

  @DeleteMapping("/tags/{id}")
  public void delete(@PathVariable Long id) {
    tagServiceImpl.deleteTag(id);
  }
}