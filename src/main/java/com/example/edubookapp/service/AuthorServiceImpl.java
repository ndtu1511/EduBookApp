package com.example.edubookapp.service;

import com.example.edubookapp.model.Author;
import com.example.edubookapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    @Transactional
    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    @Transactional
    public Long countAll() {
        return authorRepository.count();
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
