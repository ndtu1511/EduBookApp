package com.example.edubookapp.validator;

import com.example.edubookapp.model.PendingBook;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PendingBookValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PendingBook.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PendingBook pendingBook = (PendingBook) o;
    }
}
