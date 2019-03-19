package com.example.edubookapp.controller;

import com.example.edubookapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{id}")
    public String show(@PathVariable("id") Integer id, Model model){
        model.addAttribute("category", categoryService.findOne(id).get());
        model.addAttribute("categories",categoryService.findAll());
        return "category_detail";
    }
}
