package com.itacademy.cms.controller;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryController {


    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> showAllCategories(){
        List<Category> allCategories = categoryService.getAllCategories();
        return allCategories;

//    @GetMapping("/categories")
//        public ResponseEntity<List<CategoryDTO>> findall(){
//            return categoryService.getAllCategories().forEach(lambda);
//        }
    }
}
