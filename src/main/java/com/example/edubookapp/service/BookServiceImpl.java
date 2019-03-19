package com.example.edubookapp.service;

import com.example.edubookapp.model.Author;
import com.example.edubookapp.model.Book;
import com.example.edubookapp.model.Category;
import com.example.edubookapp.model.Publisher;
import com.example.edubookapp.repository.AuthorRepository;
import com.example.edubookapp.repository.BookRepository;
import com.example.edubookapp.repository.PublisherRepository;
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
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
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
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
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
    public Book upload(Book book, MultipartFile imageFile) {
        try {
            byte[] bytes = imageFile.getBytes();
            String fileName = imageFile.getOriginalFilename();
            String fileLocation = new File(Const.UPLOAD_FOLDER).getAbsolutePath() +"\\"+fileName;
            System.out.println(fileLocation);
            FileOutputStream fos = new FileOutputStream(fileLocation);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        book.setImageLink(imageFile.getOriginalFilename());
        return bookRepository.save(book);
    }

    @Override
    public Book register(Book book) {
        String[] authors = book.getAuthorsName().split("[^a-zA-Z -'.]");
        for (String authorName: authors){
            authorName = authorName.replaceAll("^\\s+|\\s+$","");
            if (authorRepository.findByName(authorName)==null){
                authorRepository.save(new Author(authorName));
            }
            book.addAuthor(authorRepository.findByName(authorName));
        }
        String publisherName = book.getPublisherName().replaceAll("^\\s+|\\s+$","");
        if (publisherRepository.findByName(publisherName)==null){
            publisherRepository.save(new Publisher(publisherName));
        }
        book.setPublisher(publisherRepository.findByName(publisherName));
        return bookRepository.save(book);
    }
}
