package com.example.edubookapp.controller.androidapi;


import com.example.edubookapp.model.Book;
import com.example.edubookapp.model.Category;
import com.example.edubookapp.payload.BookIntroduce;
import com.example.edubookapp.payload.CategoryResponse;
import com.example.edubookapp.service.CategoryService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryApi {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> indexCate() {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        List<Category> categories = Lists.newArrayList(categoryService.findAll());
        for (Category category : categories) {
            categoryResponses.add(new CategoryResponse(category.getId(), category.getName(), category.getImageLink()));
        }
        return categoryResponses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showCate(@PathVariable("id") Integer id) {
        List<BookIntroduce> bookIntroduces = new ArrayList<>();
        Category category = categoryService.findOne(id).get();
        if (!category.getBooks().isEmpty()) {
            for (Book book : category.getBooks()) {
                bookIntroduces.add(new BookIntroduce(book.getId(), book.getTitle(), book.getImageLink()));
            }
            return ResponseEntity.ok(bookIntroduces);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There is no book");
    }
}
