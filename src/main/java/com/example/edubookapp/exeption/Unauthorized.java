package com.example.edubookapp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Unauthorized extends RuntimeException {
    private Unauthorized(String message){
        super(message);
    }
    private Unauthorized(String message, Throwable cause){
        super(message,cause);
    }
}
