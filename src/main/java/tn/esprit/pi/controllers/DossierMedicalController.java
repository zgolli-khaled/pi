package tn.esprit.pi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Appointment;
import tn.esprit.pi.entities.DossierMedical;
import tn.esprit.pi.services.DossierMedicalInterfaceService;

import java.util.List;

@RestController
@RequestMapping("/dossierMedical")
public class DossierMedicalController {
    @Autowired
    DossierMedicalInterfaceService dossierMedicalInterfaceService;


    @GetMapping("/all")
    public ResponseEntity<List<DossierMedical>> getDossierMedicals(){
        return new ResponseEntity<List<DossierMedical>>(dossierMedicalInterfaceService.findALL(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DossierMedical> addDossierMedical(@RequestBody DossierMedical dossierMedical){
        DossierMedical doss =dossierMedicalInterfaceService.save(dossierMedical);
        return new ResponseEntity<DossierMedical>(doss, HttpStatus.OK);

    }


    @GetMapping("/get/{id}")
    public ResponseEntity<DossierMedical> getDossierMedical(@PathVariable("id")Long id_dossierMedical){
        DossierMedical doss =dossierMedicalInterfaceService.findById(id_dossierMedical);
        return new ResponseEntity<DossierMedical>(doss, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDossierMedical(@PathVariable("id")Long id_dossierMedical){
        dossierMedicalInterfaceService.delete(id_dossierMedical);
        return new ResponseEntity<String>("Appointment is deleted successufully!", HttpStatus.OK);
    }



    @PutMapping("/update")
    public ResponseEntity<DossierMedical> updateDossierMedical(@RequestBody DossierMedical doss){
        dossierMedicalInterfaceService.save(doss);
        return new ResponseEntity<DossierMedical>(doss, HttpStatus.OK);

    }


}
