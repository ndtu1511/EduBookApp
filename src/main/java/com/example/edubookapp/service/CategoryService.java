package com.example.edubookapp.service;

import com.example.edubookapp.model.Category;

import java.util.Optional;

public interface CategoryService {
    Iterable<Category> findAll();
    Optional<Category> findOne(Integer id);
    void save(Category category);
    Long countAll();
}
