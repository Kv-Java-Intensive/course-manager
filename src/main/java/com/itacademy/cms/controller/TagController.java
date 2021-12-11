package com.itacademy.cms.controller;


import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.TagDto;
import com.itacademy.cms.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
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
  public TagDto getTagByUuid(@PathVariable("id") String uuid) {
    return tagMapper.tagToTagDto(tagService.findByUuid(uuid));

  }

  @PostMapping("/tags")
  public void saveTag(@RequestBody TagDto tagDto) {
    tagService.saveTag(tagDto);
  }

  @PutMapping("/tags/{id}")
  public void updateUser(@RequestBody TagDto tagDto, @PathVariable("id") String uuid) {
    tagService.updateTag(tagDto, uuid);
  }

  @Transactional
  @DeleteMapping("/tags/{id}")
  public void deleteTag(@PathVariable("id") String uuid) {
    tagService.deleteTagByUuid(uuid);
  }
}
