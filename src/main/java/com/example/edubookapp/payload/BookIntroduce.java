package com.example.edubookapp.payload;

public class BookIntroduce {
    private int id;
    private String title;
    private String imageLink;

    public BookIntroduce(int id, String title, String imageLink) {
        this.id = id;
        this.title = title;
        this.imageLink = imageLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
