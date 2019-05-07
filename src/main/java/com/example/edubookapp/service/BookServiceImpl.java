package com.example.edubookapp.service;

import com.example.edubookapp.model.Book;
import com.example.edubookapp.model.PendingBook;
import com.example.edubookapp.repository.BookRepository;
import com.example.edubookapp.repository.UserRepository;
import com.example.edubookapp.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public List<Book> search(String s) {
        return bookRepository.findByTitleContaining(s);
    }

    @Override
    @Transactional
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    @Transactional
    public long countAll() {
        return bookRepository.count();
    }

    @Override
    @Transactional
    public List<Book> findLatest(int page, int size) {
        return bookRepository.findTop10ByOrderByIdDesc();
    }

    @Override
    @Transactional
    public List<Book> findByCategoryId(Integer categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }

    @Override
    @Transactional
    public Book findOneWithCategory(Integer id) {
        return bookRepository.findOneWithCategory(id);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Book> findOne(Integer id) {

        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public void writeFile(MultipartFile file, String path) {
        try {
            byte[] bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            String fileLocation = new File(path).getAbsolutePath() + "/" + fileName;
            FileOutputStream fos = new FileOutputStream(fileLocation);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public Book uploadImage(Book book, MultipartFile file) {
        writeFile(file, Const.UPLOAD_IMAGE);
        book.setImageLink(file.getOriginalFilename());
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book uploadContent(Book book, MultipartFile file) {
        writeFile(file, Const.UPLOAD_PDF);
        book.setContentLink(file.getOriginalFilename());
        return bookRepository.save(book);
    }

    @Override
    public Book registerPending(PendingBook pendingBook) {
        Book book = new Book();
        book.setTitle(pendingBook.getTitle());
        book.setBrief(pendingBook.getBrief());
        book.setImageLink(pendingBook.getImageLink());
        book.setCategory(pendingBook.getCategory());
        book.setUser(userRepository.findByUsername(pendingBook.getRequestUsername()));
        book.setLanguage(pendingBook.getLanguage());
        book.setPublishDate(pendingBook.getPublishDate());
        book.setAuthorName(pendingBook.getAuthorName());
        book.setPublisherName(pendingBook.getPublisherName());
        book.setContentLink(pendingBook.getContentLink());
        book.setPages(pendingBook.getPages());
        return bookRepository.save(book);
    }
    @Override
    public Book register(Book book) {
        return bookRepository.save(book);
    }


    @Override
    public List<Book> findByUserId(Integer userId) {
        return bookRepository.findByUserId(userId);
    }
}
