package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.pi.Interfaces.Ireclamation;
import tn.esprit.pi.entities.Reclamation;
import tn.esprit.pi.entities.Status;
import tn.esprit.pi.repositories.ReclamationRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReclamationServices implements Ireclamation {
    @Autowired
    ReclamationRepo reclamationRepo;
    @Override
    public ResponseEntity<Reclamation> createReclamation(Reclamation reclamation) {

        try{
            reclamation.setStatus(Status.non_traitée);
            reclamation.setDate(LocalDateTime.now());
            reclamationRepo.save(reclamation);
            return new ResponseEntity<>(reclamation, HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<HttpStatus> deleteReclamation(Long id) {
       try{
           reclamationRepo.deleteById(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }catch (Exception exception){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);       }

    }

    @Override
    public ResponseEntity<Reclamation> updateReclamation(Long id, Reclamation reclamation) {
        try {
            Optional<Reclamation>  rec = reclamationRepo.findById(id);
            if (rec.isPresent()) {
                Reclamation reclam = rec.get();
                reclam.setStatus(Status.non_traitée);
                reclam.setDate(LocalDateTime.now());
                reclam.setObjet(reclamation.getObjet());
                reclam.setDescription(reclamation.getDescription());
                reclamationRepo.save(reclam);
                return new ResponseEntity<>(reclam, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Reclamation>> DisplayReclamation() {
try{
    List<Reclamation> list =reclamationRepo.findAll();
    if(list.isEmpty())
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    else
        return new ResponseEntity<>(list,HttpStatus.OK);
}
catch (Exception exception) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}
    }

    @Override
    public ResponseEntity <Reclamation> DisplayReclamationByID(Long id) {
        try{
             Optional<Reclamation> rec =reclamationRepo.findById(id);
            if(rec.isPresent()){
                Reclamation reclam = rec.get();
                return new ResponseEntity<>(reclam,HttpStatus.OK);  }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
