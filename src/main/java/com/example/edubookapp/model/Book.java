package com.example.edubookapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
public class Book implements Serializable {
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

    @Column(name = "content_link")
    private String contentLink;
    @NotNull
    @Range(min = 1, max = 9999)
    @Column(name = "number_of_pages", nullable = false)
    private Integer pages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","books"})
    private Category category;


    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Download> downloads = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_user_id", referencedColumnName = "id")
    private User user;

    @NotEmpty
    @Column(name = "publisher_name", nullable = false)
    private String publisherName;

    @NotEmpty
    @Column(name = "author_name", nullable = false)
    private String authorName;
    public Book() {
        this.imageLink=null;
        this.contentLink = null;
    }

    public Book(String title, String brief, String language, Date publishDate,
                String imageLink, Category category, String publisherName,
                String authorName, User user, String contentLink, Integer pages) {
        this.title = title;
        this.brief = brief;
        this.language = language;
        this.publishDate = publishDate;
        this.imageLink = imageLink;
        this.category = category;
        this.publisherName = publisherName;
        this.authorName = authorName;
        this.user = user;
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

    public Category getCategory() {
        return category;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comment) {
        comments.add(comment);
        comment.setBook(this);
    }
    public void removeComments(Comment comment) {
        comments.remove(comment);
        comment.setBook(null);
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void addLikes(Like like) {
        likes.add(like);
        like.setBook(this);
    }

    public void removeLikes(Like like) {
        likes.remove(like);
        like.setBook(null);
    }

    public List<Download> getDownloads() {
        return downloads;
    }

    public void addDownloads(Download download) {
        downloads.add(download);
        download.setBook(this);
    }

    public void removeDownloads(Download download) {
        downloads.remove(download);
        download.setBook(null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
