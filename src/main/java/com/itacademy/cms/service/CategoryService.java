package com.itacademy.cms.service;

import com.itacademy.cms.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategories();

    public void saveCategory (Category category);

    public void deleteCategory(int id);
}