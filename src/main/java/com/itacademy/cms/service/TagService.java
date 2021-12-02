package com.itacademy.cms.service;

import com.itacademy.cms.model.Tag;

import java.util.List;

public interface TagService {
    public List<Tag> getAllTags();

    public void saveTag(Tag tag);

    public void findTagbyId(long id);

    public void deleteTag(long id);


}
