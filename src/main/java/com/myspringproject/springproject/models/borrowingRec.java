package com.myspringproject.springproject.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity


public class borrowingRec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @NotNull(message = "book id is required")

    private book book;

    @ManyToOne
    @JoinColumn(name = "patron_id", referencedColumnName = "id")
    @NotNull(message = "patron id is required")

    private patron patron;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    // Constructors, getters, and setters

    public borrowingRec() {
    }

    public borrowingRec(book book, patron patron, LocalDate borrowDate) {
        super();
        this.book = book;
        this.patron = patron;
        this.borrowDate = borrowDate;
    }

    // Getters and Setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public book getBook() {
        return book;
    }

    public void setBook(book book) {
        this.book = book;
    }

    public patron getPatron() {
        return patron;
    }

    public void setPatron(patron patron) {
        this.patron = patron;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // toString method (optional)
    @Override
    public String toString() {
        return "borrowingRec{" +
                "id=" + id +
                ", book=" + book +
                ", patron=" + patron +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }

}
