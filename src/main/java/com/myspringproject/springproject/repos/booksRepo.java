package com.myspringproject.springproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myspringproject.springproject.models.book;

@Repository
public interface booksRepo extends JpaRepository<book, Long> {
    // You can add custom query methods if needed
}
