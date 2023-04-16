package tn.esprit.pi.controllers.Api;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Dossier_Medical;

import java.util.List;

public interface DossierApi {

    @PostMapping(value = "/dossier/create",consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    Dossier_Medical save (@RequestBody Dossier_Medical dossier_medical );

    @PostMapping(value = "/dossier/create/{id}",consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    Dossier_Medical saveP (@RequestBody Dossier_Medical dossier_medical , @PathVariable("id") Long id);

    @GetMapping("/dossier/list")
    List<Dossier_Medical> findAll();
    @GetMapping(value = "/dossier/{id}")
    Dossier_Medical findById(@PathVariable("id") Long id);
    @DeleteMapping("/dossier/{id}" )
    void delete(@PathVariable("id") Long id);
    @PutMapping(value = "/dossier/update/{id}",consumes =MediaType.APPLICATION_JSON_VALUE , produces =MediaType.APPLICATION_JSON_VALUE )

    ResponseEntity<Dossier_Medical> updatedossier(@PathVariable Long id, @RequestBody Dossier_Medical dossier);
}
