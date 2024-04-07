package com.myspringproject.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myspringproject.springproject.services.borrowingRecService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/borrowingRec")
@Validated
public class borrowingRecController {
    @Autowired
    private borrowingRecService borrowingRecService;

    
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@Valid @PathVariable Long bookId, @PathVariable Long patronId) {
        boolean borrowed = borrowingRecService.borrowBook(bookId, patronId);
        if (borrowed) {
            return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to borrow book. Book or patron not found or book already borrowed.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@Valid @PathVariable Long bookId, @PathVariable Long patronId) {
        boolean returned = borrowingRecService.returnBook(bookId, patronId);
        if (returned) {
            return new ResponseEntity<>("Book returned successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to return book. Borrowing record not found.", HttpStatus.NOT_FOUND);
        }
    }


    








}
