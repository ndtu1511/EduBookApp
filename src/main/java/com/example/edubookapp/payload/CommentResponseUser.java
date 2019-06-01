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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
