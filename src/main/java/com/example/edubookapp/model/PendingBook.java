package com.example.edubookapp.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pending_book")
public class PendingBook implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @NotEmpty
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty
    @Column(name = "brief", nullable = false)
    private String brief;

    @NotEmpty
    @Length(max = 20)
    @Column(name = "language", nullable = false)
    private String language;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "publish_date", nullable = false)
    private Date publishDate;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @NotEmpty
    @Column(name = "author_name", nullable = false)
    private String authorName;

    @NotEmpty
    @Column(name = "publisher_name", nullable = false)
    private String publisherName;

    @Column(name = "request_username", nullable = false)
    private String requestUsername;

    @Column(name = "content_link")
    private String contentLink;
    @NotNull
    @Range(min = 1, max = 9999)
    @Column(name = "number_of_pages", nullable = false)
    private Integer pages;

    public PendingBook() {
    }

    public PendingBook(String title, String brief,
                       String language, Date publishDate,
                       String imageLink, Category category,
                       String authorName, String publisherName,
                       String requestUsername, String contentLink,
                       Integer pages) {
        this.title = title;
        this.brief = brief;
        this.language = language;
        this.publishDate = publishDate;
        this.imageLink = imageLink;
        this.category = category;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.requestUsername = requestUsername;
        this.contentLink = contentLink;
        this.pages = pages;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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

    public String getRequestUsername() {
        return requestUsername;
    }

    public void setRequestUsername(String requestUsername) {
        this.requestUsername = requestUsername;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
