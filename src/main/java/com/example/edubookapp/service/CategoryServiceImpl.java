package com.example.edubookapp.service;

import com.example.edubookapp.model.Category;
import com.example.edubookapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    @Transactional
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Category> findOne(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Long countAll() {
        return categoryRepository.count();
    }
}
