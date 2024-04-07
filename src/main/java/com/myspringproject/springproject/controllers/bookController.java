package com.myspringproject.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.myspringproject.springproject.models.book;
import com.myspringproject.springproject.services.booksService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Validated

public class bookController {

    @Autowired
    private booksService bookService;

    @GetMapping()
    public ResponseEntity<?> getAllBooks() {
        List<book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);

        
    }

     @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@Valid @PathVariable Long id) {
        book book = bookService.getBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("book not found",HttpStatus.NOT_FOUND);
        }
    }

     @PostMapping
    public ResponseEntity<book> addBook(@Valid @RequestBody book book) {
        book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@Valid @PathVariable Long id, @RequestBody book updatedBook) {
        book book = bookService.updateBook(id, updatedBook);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("book not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@Valid @PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("book not found",HttpStatus.NO_CONTENT);
        }
    }

}

