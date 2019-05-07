package com.example.edubookapp.payload;

import java.util.Date;
import java.util.List;

public class BookDetailResponse {
    private int id;
    private String title;
    private String authorName;
    private String publisherName;
    private String language;
    private String brief;
    private Integer pages;
    private String imageLink;
    private String contentLink;
    private int numberOfLike;
    private Date publishDate;
    private List<CommentResponse> commentResponses;

    public BookDetailResponse(int id, String title, String authorName,
                              String publisherName, String language, String brief,
                              Integer pages, String imageLink, String contentLink,
                              int numberOfLike, List<CommentResponse> commentResponses,
                              Date publishDate) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.language = language;
        this.brief = brief;
        this.pages = pages;
        this.imageLink = imageLink;
        this.contentLink = contentLink;
        this.numberOfLike = numberOfLike;
        this.commentResponses = commentResponses;
        this.publishDate = publishDate;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public int getNumberOfLike() {
        return numberOfLike;
    }

    public void setNumberOfLike(int numberOfLike) {
        this.numberOfLike = numberOfLike;
    }

    public List<CommentResponse> getCommentResponses() {
        return commentResponses;
    }

    public void setCommentResponses(List<CommentResponse> commentResponses) {
        this.commentResponses = commentResponses;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
