package com.engineer.library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String author;
    @Column(name = "image_s3_id")
    private String imageS3Id;
    private boolean isVisible;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "book_id")
    private List<GuestBook> guestBooks;

    @Transient
    private String nextPageUrl;
    @Transient
    private String prevPageUrl;

    public Book() {}

    public Book(String name, String author, String imageS3Id, boolean isVisible) {
        this.name = name;
        this.author = author;
        this.imageS3Id = imageS3Id;
        this.isVisible = isVisible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageS3Id() {
        return imageS3Id == null ? "" : imageS3Id;
    }

    public void setImageS3Id(String imageS3Id) {
        this.imageS3Id = imageS3Id;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public List<GuestBook> getGuestBooks() {
        return guestBooks;
    }

    public void setGuestBooks(List<GuestBook> guestBooks) {
        this.guestBooks = guestBooks;
    }
}
