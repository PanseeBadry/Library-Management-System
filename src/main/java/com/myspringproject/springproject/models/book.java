package com.myspringproject.springproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity


@Table(name = "books")
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Publication year is required")
    private int publicationYear;

    @NotBlank(message = "ISBN is required")
    private String ISBN;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean borrowed; // Indicates if the book is currently borrowed

    
    public book(){
        
    }

    public book(Long id, String title, String author, int publicationYear, String iSBN) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.ISBN = iSBN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        this.ISBN = iSBN;
    }

    


    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }



    @Override
    public String toString() {
        return "book [id=" + id + ", publicationYear=" + publicationYear + "]";
    }

    

    

    
    
    
    // Constructors, getters, and setters

    
    
    

}
