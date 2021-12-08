package com.itacademy.cms.controller;


import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.TagDto;
import com.itacademy.cms.service.TagService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagController {

  private final TagService tagService;

  private final MapStructMapper tagMapper;


  @GetMapping("/tags")
  public List<TagDto> showAllTags() {
    return tagService.getAllTags().stream()
        .map(tagMapper::tagToTagDto).collect(Collectors.toList());
  }

  @GetMapping("/tags/{id}")
  public TagDto getTagById(@PathVariable("id") UUID tagId) {
    return tagMapper.tagToTagDto(tagService.findTagbyId(tagId));

  }

  @PostMapping("/tags")
  public void saveTag(@RequestBody TagDto tagDto) {
    tagService.saveTag(tagDto);
  }

  @PutMapping("/tags/{id}")
  public void updateUser(@RequestBody TagDto tagDto, @PathVariable UUID id) {
    tagService.updateTag(tagDto, id);
  }

  @DeleteMapping("/tags/{id}")
  public void delete(@PathVariable UUID id) {
    tagService.deleteTag(id);
  }
}
