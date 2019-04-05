package com.example.edubookapp.controller.androidapi;


import com.example.edubookapp.model.Book;
import com.example.edubookapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookApi {
    @Autowired
    private BookService bookService;
    @GetMapping("/api/book")
    public Iterable<Book> index(){
        return bookService.findAll();
    }
    @GetMapping("/api/book/{id}")
    public Book show(@PathVariable("id") Integer id){
        return bookService.findOne(id).get();
    }
}
