package tn.esprit.pi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.controllers.Api.PrescriptionApi;
import tn.esprit.pi.entities.Prescription;
import tn.esprit.pi.services.PrescriptionService;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class PrescriptionController implements PrescriptionApi {

private final PrescriptionService prescriptionService;
@Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


    @Override
    public Prescription save (Prescription prescription,Long id) {

        return prescriptionService.save(prescription,id);
    }

    @Override
    public List<Prescription> findAll() {
        return prescriptionService.findAll();
    }

    @Override
    public Prescription findById(Long id) {

        return prescriptionService.findById(id);
    }

    @Override
    public void delete(Long id) {
      prescriptionService.delete(id);
    }

    @Override
    public ResponseEntity<Prescription> update(Long id, Prescription prescription) {
        Prescription prescription0 = prescriptionService.findById(id);
        Long id_dossier = prescription0.getDossier_medical().getId();


        if(prescription0 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            prescription0.setTitle(prescription.getTitle());
            prescription0.setContent(prescription.getContent());

            return new ResponseEntity<>(prescriptionService.save(prescription0,id_dossier), HttpStatus.CREATED);
        }catch(DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
