package tn.esprit.pi.controllers.Api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.treatment;

import java.util.List;

public interface treatmentApi {

    @PostMapping(value = "/treatment/create/{id}",consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    treatment save (@RequestBody treatment treatment );

    @GetMapping("/treatment/list")
    List<treatment> findAll();
    @GetMapping(value = "/treatment/{id}")
    treatment findById(@PathVariable Long id);
    @DeleteMapping("/treatment/{id}" )
    void delete(@PathVariable("id") Long id);


}
