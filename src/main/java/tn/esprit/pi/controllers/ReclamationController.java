package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.Interfaces.Ireclamation;
import tn.esprit.pi.entities.Reclamation;

import java.util.List;

@RestController
@RequestMapping("/reclamation")
public class ReclamationController {
    @Autowired
    Ireclamation ireclamation;
    @PostMapping("/createReclamation")
    public ResponseEntity<Reclamation> createReclamation (@RequestBody Reclamation reclamation)
    {
        return ireclamation.createReclamation(reclamation);
    }
    @DeleteMapping ("/deleteReclamation/{id}")
    public ResponseEntity <HttpStatus> deleteReclamation (@PathVariable Long id)
    {
        return ireclamation.deleteReclamation(id);
    }
    @PutMapping ("/updateReclamation/{id}")
    public ResponseEntity <Reclamation> UpdateReclamation (@PathVariable("id") Long id,@RequestBody Reclamation reclamation)
    {
        return ireclamation.updateReclamation(id, reclamation);
    }

    @GetMapping("/displayreclamation")
    public ResponseEntity <List<Reclamation>> DisplayReclamation()
    {
        return ireclamation.DisplayReclamation();
    }
    @GetMapping("/displayreclamation/{id}")
    public ResponseEntity <Reclamation> DisplayReclamationByID(@PathVariable Long id)
    {
        return ireclamation.DisplayReclamationByID(id);
    }

}
