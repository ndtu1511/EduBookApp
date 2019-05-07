package com.example.edubookapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    private int id;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9_]+$")
    @Column(name = "username", nullable = false, unique = true,length = 100)
    private String username;

    @NotEmpty
    @Email
    @Column(name="email", nullable = false)
    private String email;
    @NotEmpty
    @Length(min = 6)
    @Column(name = "password", nullable = false, length = 60)
    @JsonIgnore
    private String password;

    @Transient
    @JsonIgnore
    private String confirmPassword;

    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","users"})
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Download> downloads = new ArrayList<>();
    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comment) {
        comments.add(comment);
        comment.setUser(this);
    }
    public void removeComments(Comment comment) {
        comments.remove(comment);
        comment.setUser(null);
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void addLikes(Like like) {
        likes.add(like);
        like.setUser(this);
    }

    public void removeLikes(Like like) {
        likes.remove(like);
        like.setUser(null);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Download> getDownloads() {
        return downloads;
    }

    public void addDownloads(Download download) {
        downloads.add(download);
        download.setUser(this);
    }

    public void removeDownloads(Download download) {
        downloads.remove(download);
        download.setUser(null);
    }

    public void addBooks(Book book) {
        books.add(book);
        book.setUser(this);
    }

    public void removeBooks(Book book) {
        books.remove(book);
        book.setUser(null);
    }
}
