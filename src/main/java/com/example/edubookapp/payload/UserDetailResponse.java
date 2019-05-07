package com.example.edubookapp.payload;

public class UserDetailResponse {
    private int id;
    private String username;
    private String email;
    private int numberOfComment;

    public UserDetailResponse(int id, String username, String email, int numberOfComment) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.numberOfComment = numberOfComment;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberOfComment() {
        return numberOfComment;
    }

    public void setNumberOfComment(int numberOfComment) {
        this.numberOfComment = numberOfComment;
    }
}
