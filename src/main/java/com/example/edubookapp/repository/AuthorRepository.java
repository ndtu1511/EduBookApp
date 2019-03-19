package com.example.edubookapp.repository;

import com.example.edubookapp.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Author findByName(String name);
}
