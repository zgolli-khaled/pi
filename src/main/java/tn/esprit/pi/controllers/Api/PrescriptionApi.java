package tn.esprit.pi.controllers.Api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.pi.entities.Prescription;

import java.util.List;

public interface PrescriptionApi {



    @GetMapping("/prescription/list")
    List<Prescription> findAll();
    @GetMapping(value = "/prescription/{id}")
   Prescription findById(@PathVariable Long id);
    @DeleteMapping("/prescription/{id}" )
    void delete(@PathVariable("id") Long id);

}
