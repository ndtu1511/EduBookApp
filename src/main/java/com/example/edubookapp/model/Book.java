package com.example.edubookapp.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @Length(max = 60)
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

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
    @JoinColumn(name = "category_id",referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","books"})
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false,referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","books"})
    private Publisher publisher;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","book"})
    private List<Comment> comments = new ArrayList<>();

    @JoinTable(
            name = "author_book",
            joinColumns = {@JoinColumn(name = "book_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id",referencedColumnName = "id")}
    )
    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("books")
    private List<Author> authors = new ArrayList<>();

    @Transient
    @JsonIgnore
    private String publisherName;
    @Transient
    @JsonIgnore
    private String authorsName;
    public Book() {
        this.imageLink=null;
    }

    public Book(String isbn, String title, String brief, String language, Date publishDate,
                String imageLink, Category category, Publisher publisher, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.brief = brief;
        this.language = language;
        this.publishDate = publishDate;
        this.imageLink = imageLink;
        this.category = category;
        this.publisher = publisher;
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }
    public void removeAuthor(Author author){
        authors.remove(author);
        author.getBooks().remove(this);
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
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
}
