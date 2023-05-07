package tn.esprit.pi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Appointment;
import tn.esprit.pi.services.AppointmentInterfaceService;
import org.springframework.format.annotation.DateTimeFormat;
import tn.esprit.pi.services.EmailSenderService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/appointment")
public class    AppoitmentController {
    @Autowired
    AppointmentInterfaceService appointmentService;

    @Autowired
    private EmailSenderService emailService;


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


    // find by date

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){

        return new ResponseEntity<List<Appointment>>(appointmentService.findAllByDate(date), HttpStatus.OK);
    }
// find defore date for sending mail

    @GetMapping("/getByDateBefore")
    public List<Appointment> getAppointmentsBeforeDate(   @RequestParam("day") Double day) {
       return  appointmentService.findAllByDateBefore(day);

    }

    /*@GetMapping("/getByDateBefore")
    public ResponseEntity<List<Appointment>> getAppointmentsBeforeDate() {
        LocalDate date= LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        Date date2 = (Date) formatter.parse(formattedDate);
        return new ResponseEntity<List<Appointment>>(appointmentService.findAllByDateBefore(date2), HttpStatus.OK);
    }*/

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


    @GetMapping("/countApp")
        public int countAppointment(@RequestParam("date")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return appointmentService.countAppointmen(date);

        }

    @PostMapping("/send/{mail}")
    ResponseEntity<String> sendEmail(@PathVariable (value = "mail") String emailDto){


        String to = emailDto;
        String subject = "rendez vous ";
        String body="test";
        emailService.sendEmail(emailDto,body,subject);
        return ResponseEntity.ok("Email sent successfully.");


    }




}
