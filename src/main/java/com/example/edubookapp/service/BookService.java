package com.example.edubookapp.service;

import com.example.edubookapp.model.Book;
import com.example.edubookapp.model.PendingBook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Iterable<Book> findAll();

    List<Book> search(String s);

    Book findByTitle(String title);


    long countAll();

    List<Book> findLatest(int page, int size);

    List<Book> findByCategoryId(Integer categoryId);

    Book findOneWithCategory(Integer id);

    Book save(Book book);

    Book registerPending(PendingBook pendingBook);

    void delete(Integer id);

    Optional<Book> findOne(Integer id);

    Book uploadImage(Book book, MultipartFile imageFile);
    Book register(Book book);

    List<Book> findByUserId(Integer userId);

    void writeFile(MultipartFile file, String path);

    Book uploadContent(Book book, MultipartFile file);
}
