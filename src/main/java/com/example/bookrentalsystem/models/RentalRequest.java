package com.example.bookrentalsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rental_request")
public class RentalRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User borrower;

    @ManyToOne
    private Book book;

    private boolean accepted;

    private String status;

    // Constructor
    public RentalRequest() {

    }

    public RentalRequest(User borrower, Book book, boolean accepted, String status) {
        this.borrower = borrower;
        this.book = book;
        this.accepted = accepted;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrowerId(User borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
