package com.engineer.library.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class GuestBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @OneToOne
    private User user;
    @OneToOne
    private Book book;
    private LocalDate dateToReturn;
    private LocalDate returnedDate;

    public GuestBook() {}

    public GuestBook(User user, Book book, LocalDate dateToReturn, LocalDate returnedDate) {
        this.user = user;
        this.book = book;
        this.dateToReturn = dateToReturn;
        this.returnedDate = returnedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateToReturn() {
        return dateToReturn;
    }

    public void setDateToReturn(LocalDate dateToReturn) {
        this.dateToReturn = dateToReturn;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
}
