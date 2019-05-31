package com.example.edubookapp.payload;

import java.util.Date;

public class CommentResponseUser {
    private int id;
    private String content;
    private Date date;
    private String bookTitle;

    public CommentResponseUser(int id, String content, Date date, String bookTitle) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.bookTitle = bookTitle;
    }
}
