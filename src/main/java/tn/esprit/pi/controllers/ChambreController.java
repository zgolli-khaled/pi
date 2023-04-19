package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Appointment;
import tn.esprit.pi.entities.Chambre;
import tn.esprit.pi.services.AppointmentInterfaceService;
import tn.esprit.pi.services.ChambreInterfaceService;

import java.util.List;

@RestController
@RequestMapping("/chambre")
public class ChambreController {
    @Autowired
    ChambreInterfaceService chambreService;

    @GetMapping("/all")
    public ResponseEntity<List<Chambre>> getChambres(){
        return new ResponseEntity<List<Chambre>>(chambreService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Chambre> addChambre(@RequestBody Chambre chambre){
        Chambre ch =chambreService.save(chambre);
        return new ResponseEntity<Chambre>(ch, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Chambre> getChambre(@PathVariable("id")Long id_chambre){
        Chambre chambre =chambreService.findById(id_chambre);
        return new ResponseEntity<Chambre>(chambre, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChambre(@PathVariable("id")Long id_chambre){
        chambreService.delete(id_chambre);
        return new ResponseEntity<String>("Chambre is deleted successufully!", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Chambre> updateChambre( @RequestBody Chambre chambre){




        chambreService.save(chambre);
        return new ResponseEntity<Chambre>(chambre, HttpStatus.OK);

    }



}

