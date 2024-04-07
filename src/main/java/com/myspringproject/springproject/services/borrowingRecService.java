package com.myspringproject.springproject.services;

import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myspringproject.springproject.models.book;
import com.myspringproject.springproject.models.borrowingRec;
import com.myspringproject.springproject.models.patron;
import com.myspringproject.springproject.repos.booksRepo;
import com.myspringproject.springproject.repos.borrowingRecRepo;
import com.myspringproject.springproject.repos.patronRepo;

@Service
public class borrowingRecService {

    @Autowired
    private borrowingRecRepo borrowingRecRepo;

    @Autowired
    private booksRepo booksRepo;

    @Autowired
    private patronRepo patronRepo;

    @Transactional
    public boolean borrowBook(Long bookId, Long patronId) {
        // Check if book exists
        // @SuppressWarnings("null")
        book book = booksRepo.findById(bookId).orElse(null);
        if (book == null) {
            return false; // Book not found
        }

        // Check if patron exists
        // @SuppressWarnings("null")
        patron patron = patronRepo.findById(patronId).orElse(null);
        if (patron == null) {
            return false; // Patron not found
        }

        // Check if the book is already borrowed
        if (book.isBorrowed()) {
            return false; // Book is already borrowed
        }

        // Create a borrowing record and save it
        borrowingRec borrowingRecord = new borrowingRec(book, patron, LocalDate.now());
        borrowingRecRepo.save(borrowingRecord);

        // Update book status to borrowed
        book.setBorrowed(true);
        booksRepo.save(book);

        return true; // Successfully borrowed book
    }

    public boolean returnBook(Long bookId, Long patronId) {
        // Find the borrowing record
        borrowingRec borrowingRecord = borrowingRecRepo.findByBookIdAndPatronId(bookId, patronId);
        if (borrowingRecord == null) {
            return false; // Borrowing record not found
        }

        // Update the return date
        borrowingRecord.setReturnDate(LocalDate.now());
        borrowingRecRepo.save(borrowingRecord);

        // Update book status to available if needed
        book book = borrowingRecord.getBook();
        if (book.isBorrowed()) {
            book.setBorrowed(false);
            booksRepo.save(book);
        }

        return true; // Successfully returned book
    }

}
