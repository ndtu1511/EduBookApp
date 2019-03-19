package com.example.edubookapp.service;

import com.example.edubookapp.model.Author;

public interface AuthorService {
    Author findByName(String name);
    Long countAll();
    Author save(Author author);
}
