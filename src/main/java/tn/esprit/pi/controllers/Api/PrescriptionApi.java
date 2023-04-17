package tn.esprit.pi.controllers.Api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Dossier_Medical;
import tn.esprit.pi.entities.Prescription;

import java.util.List;

public interface PrescriptionApi {

    @PostMapping(value = "/prescription/create/{id}",consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    Prescription save (@RequestBody Prescription prescription ,  @PathVariable("id") Long id);

    @GetMapping("/prescription/list")
    List<Prescription> findAll();
    @GetMapping(value = "/prescription/{id}")
   Prescription findById(@PathVariable Long id);
    @DeleteMapping("/prescription/{id}" )
    void delete(@PathVariable("id") Long id);
    @PutMapping(value = "/prescription/update/{id}",consumes =MediaType.APPLICATION_JSON_VALUE , produces =MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<Prescription> update(@PathVariable Long id, @RequestBody Prescription prescription);

}
