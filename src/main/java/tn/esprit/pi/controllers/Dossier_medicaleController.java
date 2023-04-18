package tn.esprit.pi.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.controllers.Api.DossierApi;
import tn.esprit.pi.entities.Dossier_Medical;
import tn.esprit.pi.services.Dossier_MedicalService;

import java.util.List;

@RestController
@Slf4j
public class Dossier_medicaleController implements DossierApi {



    private final Dossier_MedicalService dossier_MedicalService;

    @Autowired
    public Dossier_medicaleController(Dossier_MedicalService dossier_MedicalService) {
        this.dossier_MedicalService = dossier_MedicalService;
    }



    @Override
    public Dossier_Medical save(Dossier_Medical dossier_medical) {
        return dossier_MedicalService.savewithPatient(dossier_medical);
    }

    @Override
    public Dossier_Medical saveP(Dossier_Medical dossier_medical, Long id) {
        return dossier_MedicalService.SaveWithExistPatient(dossier_medical,id);
    }


    @Override
    public List<Dossier_Medical> findAll() {
        return dossier_MedicalService.findAll();
    }

    @Override
    public Dossier_Medical findById(Long id) {

        return dossier_MedicalService.findById(id);
    }

    @Override
    public List<Dossier_Medical> find_By_Patient_Name(String Patient_Name) {
        return dossier_MedicalService.find_By_Patient_Name(Patient_Name);
    }

    @Override
    public List<Dossier_Medical> Search(String keyword) {
        return dossier_MedicalService.Search(keyword);
    }


    @Override
    public void delete(Long id) {
    dossier_MedicalService.delete(id);
    }

    @Override
    public ResponseEntity<Dossier_Medical> updatedossier( Long id,  Dossier_Medical dossier){
        Dossier_Medical dossier0 = dossier_MedicalService.findById(id);
         Long id_patient = dossier0.getUser().getId();


        if(dossier0 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            dossier0.setMotif_admission(dossier.getMotif_admission());
            dossier0.setService_hospitalier(dossier.getService_hospitalier());
            dossier0.setAntecedents(dossier.getAntecedents());
            dossier0.setDatecreation(dossier.getDatecreation());

            return new ResponseEntity<>(dossier_MedicalService.SaveWithExistPatient(dossier0,id_patient), HttpStatus.CREATED);
        }catch(DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
