package com.example.edubookapp.payload;

import java.util.Date;

public class DownloadResponse {
    private int id;
    private String username;
    private String bookTitle;
    private int currentPage;
    private Date date;

    public DownloadResponse(int id, String username, String bookTitle, int currentPage, Date date) {
        this.id = id;
        this.username = username;
        this.bookTitle = bookTitle;
        this.currentPage = currentPage;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
