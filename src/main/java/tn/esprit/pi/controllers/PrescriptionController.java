package tn.esprit.pi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.controllers.Api.PrescriptionApi;
import tn.esprit.pi.entities.Prescription;
import tn.esprit.pi.services.PrescriptionService;
import tn.esprit.pi.services.treatmentService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Slf4j
public class PrescriptionController implements PrescriptionApi {

private final PrescriptionService prescriptionService;
    private final treatmentService treatmentService;
@Autowired
    public PrescriptionController(PrescriptionService prescriptionService, tn.esprit.pi.services.treatmentService treatmentService) {
        this.prescriptionService = prescriptionService;
    this.treatmentService = treatmentService;
}


    @PostMapping("/prescription/create")

        public ResponseEntity<Prescription> save(@RequestBody Prescription prescription ) {
            Prescription savedPrescription = prescriptionService.save(prescription);
            return new ResponseEntity<>(savedPrescription, HttpStatus.CREATED);
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

    @PutMapping("/{id}")
    public Prescription updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {
        prescription.setId(id);
        return prescriptionService.update(prescription);
    }
}
