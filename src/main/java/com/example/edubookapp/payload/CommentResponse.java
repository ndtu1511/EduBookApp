package com.example.edubookapp.payload;

import java.util.Date;

public class CommentResponse {
    private int id;
    private String content;
    private Date date;
    private String username;

    public CommentResponse(int id, String content, Date date, String username) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
