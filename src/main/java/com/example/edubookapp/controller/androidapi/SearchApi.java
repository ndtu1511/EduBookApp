package com.example.edubookapp.controller.androidapi;

import com.example.edubookapp.model.Book;
import com.example.edubookapp.payload.BookIntroduce;
import com.example.edubookapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchApi {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> search(@RequestParam(value = "s", required = false) String s) {
        List<BookIntroduce> bookIntroduces = new ArrayList<>();
        if (!bookService.search(s).isEmpty()) {
            for (Book book : bookService.search(s)) {
                bookIntroduces.add(new BookIntroduce(book.getId(), book.getTitle(), book.getImageLink()));
            }
            return ResponseEntity.ok(bookIntroduces);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Can not found book contain " + s);
    }
}
