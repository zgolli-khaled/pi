package tn.esprit.pi.controllers.Api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Prescription;
import tn.esprit.pi.entities.treatment;

import java.util.List;

public interface PrescriptionApi {



    @GetMapping("/prescription/list")
    List<Prescription> findAll();
    @GetMapping(value = "/prescription/{id}")
   Prescription findById(@PathVariable Long id);
    @DeleteMapping("/prescription/{id}" )
    void delete(@PathVariable("id") Long id);

}
