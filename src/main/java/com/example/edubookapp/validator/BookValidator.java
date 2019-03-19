package com.example.edubookapp.validator;

import com.example.edubookapp.controller.admin.AdminBookController;
import com.example.edubookapp.model.Book;
import com.example.edubookapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    @Autowired
    private BookService bookService;
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publisherName", "NotEmpty.book.publisherName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "authorsName", "NotEmpty.book.authorsName");

        if (AdminBookController.urlAdd.equalsIgnoreCase("/admin/book/add")) {
            if (bookService.findByIsbn(book.getIsbn()) != null) {
                errors.rejectValue("isbn", "Exits.book.isbn");
            }
        }
    }
}
