package tn.esprit.pi.Interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.pi.entities.Reclamation;
import tn.esprit.pi.entities.User;

import java.util.List;

public interface Ireclamation {
    ResponseEntity <Reclamation> createReclamation (Reclamation reclamation);
    ResponseEntity <HttpStatus> deleteReclamation (Long id);
    ResponseEntity <Reclamation> updateReclamation (Long id , Reclamation reclamation);
    ResponseEntity <List<Reclamation>> DisplayReclamation();
    ResponseEntity <Reclamation> DisplayReclamationByID(Long id);




}
