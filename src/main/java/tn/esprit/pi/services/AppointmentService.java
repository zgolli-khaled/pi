package tn.esprit.pi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Appointment;
import tn.esprit.pi.repositories.AppointmentRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements AppointmentInterfaceService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Override


    public List<Appointment> findALL() {
        return  appointmentRepository.findAll();
    }

    @Override
    public Appointment save(Appointment app) {
         appointmentRepository.save(app);
        return app;
    }

    @Override
    public Appointment findById(Long id) {
        if(appointmentRepository.findById(id).isPresent()){
            return  appointmentRepository.findById(id).get();

        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Appointment appointment = findById(id);
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<Appointment> findAllByDate(Date date) {
        return appointmentRepository.findAppointmentsByDateApp(date);
    }

    @Override
    public List<Appointment> findAllByDateBefore(Double day) {
        return appointmentRepository.findAppointmentsByDateBefore(day);
    }

    @Override
    public int countAppointmen(Date day) {
        return  appointmentRepository.countAppointmen( day);
    }




   /* @Override
    public List<Appointment> findAllByDateBefore(Date date) {
        return appointmentRepository.findAppointmentsByDateAppBefore(date);
    }
     /*
    */



}
