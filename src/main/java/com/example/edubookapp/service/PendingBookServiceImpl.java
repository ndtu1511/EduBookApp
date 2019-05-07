package com.example.edubookapp.service;

import com.example.edubookapp.model.PendingBook;
import com.example.edubookapp.repository.PendingBookRepository;
import com.example.edubookapp.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PendingBookServiceImpl implements PendingBookService {
    @Autowired
    private PendingBookRepository pendingBookRepository;
    @Autowired
    private BookService bookService;

    @Override
    public List<PendingBook> findByRequestUsername(String username) {
        return pendingBookRepository.findByRequestUsername(username);
    }

    @Override
    @Transactional
    public PendingBook findByTitle(String title) {
        return pendingBookRepository.findByTitle(title);
    }

    @Override
    public PendingBook findByIdAndRequestUsername(Integer id, String requestUsername) {
        return pendingBookRepository.findByIdAndRequestUsername(id, requestUsername);
    }

    @Override
    public PendingBook save(PendingBook pendingBook) {
        return pendingBookRepository.save(pendingBook);
    }

    @Override
    public PendingBook uploadImage(PendingBook pendingBook, MultipartFile file) {
        bookService.writeFile(file, Const.UPLOAD_IMAGE);
        pendingBook.setImageLink(file.getOriginalFilename());
        return pendingBookRepository.save(pendingBook);
    }

    @Override
    public PendingBook uploadContent(PendingBook pendingBook, MultipartFile file) {
        bookService.writeFile(file, Const.UPLOAD_PDF);
        pendingBook.setContentLink(file.getOriginalFilename());
        return pendingBookRepository.save(pendingBook);
    }

    @Override
    public PendingBook findOneWithCategory(Integer id) {
        return pendingBookRepository.findOneWithCategory(id);
    }

    @Override
    public Iterable<PendingBook> findAll() {
        return pendingBookRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        pendingBookRepository.deleteById(id);
    }
}
