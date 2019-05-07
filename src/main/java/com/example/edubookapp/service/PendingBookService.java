package com.example.edubookapp.service;

import com.example.edubookapp.model.PendingBook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PendingBookService {
    List<PendingBook> findByRequestUsername(String username);

    PendingBook findByTitle(String title);

    PendingBook findByIdAndRequestUsername(Integer id, String requestUsername);

    PendingBook save(PendingBook pendingBook);

    PendingBook uploadImage(PendingBook pendingBook, MultipartFile imageFile);

    PendingBook uploadContent(PendingBook pendingBook, MultipartFile imageFile);

    PendingBook findOneWithCategory(Integer id);

    Iterable<PendingBook> findAll();

    void delete(Integer id);
}
