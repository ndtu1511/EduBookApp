package com.example.edubookapp.service;

import com.example.edubookapp.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Iterable<Book> findAll();

    List<Book> search(String s);

    Book findByTitle(String title);

    Book findByIsbn(String isbn);

    long countAll();

    List<Book> findLatest(int page, int size);

    List<Book> findByCategoryId(Integer categoryId);

    Book findOneWithCategory(Integer id);

    Book save(Book book);

    void delete(Integer id);

    Optional<Book> findOne(Integer id);

    Book upload(Book book, MultipartFile imageFile);
    Book register(Book book);
}
