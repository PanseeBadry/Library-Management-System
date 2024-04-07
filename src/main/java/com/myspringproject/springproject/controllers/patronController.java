package com.myspringproject.springproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myspringproject.springproject.models.patron;
import com.myspringproject.springproject.services.patronService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/patrons")
@Validated
public class patronController {

     @Autowired
    private patronService patronService;

    @GetMapping()
    public ResponseEntity<?> getAllPatrons() {
        List<patron> patrons = patronService.getAllPatrons();
        if (patrons != null) {
            return new ResponseEntity<>(patrons, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("patrons not found",HttpStatus.NOT_FOUND);
        }
    }

     @GetMapping("/{id}")
    public ResponseEntity<?> getPatronById(@Valid @PathVariable Long id) {
        patron patron = patronService.getPatronById(id);
        if (patron != null) {
            return new ResponseEntity<>(patron, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("patron not found",HttpStatus.NOT_FOUND);
        }
    }

     @PostMapping
    public ResponseEntity<patron> addPatron(@Valid @RequestBody patron patron) {
        patron savedPatron = patronService.savePatron(patron);
        return new ResponseEntity<>(savedPatron, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron(@Valid @PathVariable Long id, @RequestBody patron updatedPatron) {
        patron patron = patronService.updatePatron(id, updatedPatron);
        if (patron != null) {
            return new ResponseEntity<>(patron, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("patron not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatron(@Valid @PathVariable Long id) {
        boolean deleted = patronService.deletePatron(id);
        if (deleted) {
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("patron not found",HttpStatus.NO_CONTENT);
        }
    }


}
