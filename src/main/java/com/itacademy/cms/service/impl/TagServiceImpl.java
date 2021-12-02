package com.itacademy.cms.service.impl;

import com.itacademy.cms.jpaRepository.TagRepository;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.service.TagService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl (TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void findTagbyId(long id) {
        tagRepository.findById(id);
    }

    @Override
    public void deleteTag(long id) {
        tagRepository.deleteById(id);
    }
}
