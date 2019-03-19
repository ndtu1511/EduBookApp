package com.example.edubookapp.controller.androidapi;

import com.example.edubookapp.model.Category;
import com.example.edubookapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryApi {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/api/cate")
    public Iterable<Category> indexCate(){
        return categoryService.findAll();
    }
    @GetMapping("/api/cate/{id}")
    public Category showCate(@PathVariable("id") Integer id){
        return categoryService.findOne(id).get();
    }
}
