package com.itacademy.cms.controller;


import com.itacademy.cms.exeption.TagNotFoundException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.TagDto;
import com.itacademy.cms.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TagController {

  private final TagService tagService;

  private final MapStructMapper tagMapper;


  @GetMapping("/tags")
  public List<TagDto> showAllTags() throws TagNotFoundException {
    return tagService.getAllTags().stream()
        .map(tagMapper::tagToTagDto).collect(Collectors.toList());
  }

  @GetMapping("/tags/{id}")
  public TagDto getTagById(@PathVariable("id") Long tagId) throws TagNotFoundException {
    return tagMapper.tagToTagDto(tagService.findTagbyId(tagId));

  }

  @PostMapping("/tags")
  public void saveTag(@RequestBody TagDto tagDto) {
    tagService.saveTag(tagDto);
  }

  @PutMapping("/tags/{id}")
  public void updateUser(@RequestBody TagDto tagDto, @PathVariable Long id) {
    tagService.updateTag(tagDto, id);
  }

  @DeleteMapping("/tags/{id}")
  public void delete(@PathVariable Long id) throws TagNotFoundException {
    tagService.deleteTag(id);
  }
}
