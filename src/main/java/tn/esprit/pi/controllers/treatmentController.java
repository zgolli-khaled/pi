package tn.esprit.pi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.controllers.Api.treatmentApi;
import tn.esprit.pi.entities.Prescription;
import tn.esprit.pi.entities.treatment;
import tn.esprit.pi.services.treatmentService;

import java.util.List;

@RestController
@Slf4j
public class treatmentController implements treatmentApi {

    @Autowired
    private  final treatmentService treatmentService ;

    public treatmentController(tn.esprit.pi.services.treatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }


    @Override
    public treatment save(treatment treatment) {


        return treatmentService.save(treatment);
    }

    @Override
    public List<treatment> findAll() {
        return treatmentService.findAll();
    }

    @Override
    public treatment findById(Long id) {

        return treatmentService.findById(id);
    }

    @Override
    public void delete(Long id) {
        treatmentService.delete(id);

    }


}
