package com.myspringproject.springproject.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringproject.springproject.models.patron;
import com.myspringproject.springproject.repos.patronRepo;
import org.springframework.transaction.annotation.Transactional;

@Service

public class patronService {

    @Autowired
    private patronRepo patronRepo;

    @Transactional
    public List<patron> getAllPatrons() {
        return (List<patron>) patronRepo.findAll();
    } 

    // @SuppressWarnings("null")
    @Transactional
    public patron getPatronById(Long id) {
        return patronRepo.findById(id).orElse(null);
    }



    // @SuppressWarnings("null")
    @Transactional
    public patron savePatron(patron patron) {
        return patronRepo.save(patron);
    }


    @Transactional
    public patron updatePatron(Long id, patron updatedPatron) {
        // @SuppressWarnings("null")
        Optional<patron> optionalPatron = patronRepo.findById(id);
        
        if (optionalPatron.isPresent()) {
            patron existingPatron = optionalPatron.get();
            
            // Update existing book with new information
            existingPatron.setName(updatedPatron.getName());
            existingPatron.setContactInformation(updatedPatron.getContactInformation());
                        
            // Save and return the updated book
            return patronRepo.save(existingPatron);
        } else {
            return null; // Or throw a custom exception, return a ResponseEntity, etc.
        }
    }

    // @SuppressWarnings("null")
    @Transactional
    public boolean deletePatron(Long id) {
        patron  foundPatron = patronRepo.findById(id).orElse(null);
        if (foundPatron != null){
            patronRepo.deleteById(id);
            return true;
        }else{
            return false;
    }
}


}
