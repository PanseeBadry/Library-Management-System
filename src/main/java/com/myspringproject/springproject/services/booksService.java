package com.myspringproject.springproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringproject.springproject.models.book;
import com.myspringproject.springproject.repos.booksRepo;

import org.springframework.transaction.annotation.Transactional;

@Service
public class booksService{


    @Autowired
    private booksRepo booksRepo;

    @Transactional
    public List<book> getAllBooks() {
        return (List<book>) booksRepo.findAll();
    } 

    @Transactional
    public book getBookById(Long id) {
        return booksRepo.findById(id).orElse(null);
    }



    // @SuppressWarnings("null")
    @Transactional
    public book saveBook(book book) {
        return booksRepo.save(book);
    }

    @Transactional
    public book updateBook(Long id, book updatedBook) {
        // @SuppressWarnings("null")
        Optional<book> optionalBook = booksRepo.findById(id);
        
        if (optionalBook.isPresent()) {
            book existingBook = optionalBook.get();
            
            // Update existing book with new information
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPublicationYear(updatedBook.getPublicationYear());
            existingBook.setISBN(updatedBook.getISBN());

                        
            // Save and return the updated book
            return booksRepo.save(existingBook);
        } else {
            return null; // Or throw a custom exception, return a ResponseEntity, etc.
        }
    }
    @Transactional
    // @SuppressWarnings("null")
    public boolean deleteBook(Long id) {
        book  foundBook = booksRepo.findById(id).orElse(null);
        if (foundBook != null){
            booksRepo.deleteById(id);
            return true;
        }else{
            return false;
    }
}


}