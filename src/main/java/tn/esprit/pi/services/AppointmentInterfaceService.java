package tn.esprit.pi.services;

import tn.esprit.pi.entities.Appointment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AppointmentInterfaceService {

    public List<Appointment> findALL();
    public Appointment save(Appointment app);
    public Appointment findById(Long id);
    public void delete(Long id);

    public List<Appointment> findAllByDate(Date date);

    public  List<Appointment> findAllByDateBefore(Double day);
    public int countAppointmen(Date day);

}