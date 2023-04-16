package tn.esprit.pi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Appointment;
import tn.esprit.pi.services.AppointmentInterfaceService;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppoitmentController {
    @Autowired
    AppointmentInterfaceService appointmentService;


    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAppointments(){
        return new ResponseEntity<List<Appointment>>(appointmentService.findALL(), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment){
        Appointment app =appointmentService.save(appointment);
        return new ResponseEntity<Appointment>(app, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable("id")Long id_appointment){
        Appointment appointment =appointmentService.findById(id_appointment);
        return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id")Long id_appointment){
        appointmentService.delete(id_appointment);
        return new ResponseEntity<String>("Appointment is deleted successufully!", HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Appointment> updateAppointment( @RequestBody Appointment appointment){




        appointmentService.save(appointment);
        return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);

    }

}
