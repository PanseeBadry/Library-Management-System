package com.myspringproject.springproject.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import com.myspringproject.springproject.models.borrowingRec;

public interface borrowingRecRepo extends JpaRepository<borrowingRec, Long> {

    borrowingRec findByBookIdAndPatronId(Long bookId, Long patronId);


}
