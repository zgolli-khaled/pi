package tn.esprit.pi.services;

import tn.esprit.pi.entities.Prescription;
import tn.esprit.pi.entities.treatment;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> findAll();
    Prescription findById(Long id);
    Prescription save (Prescription prescription );
    void delete(Long id);
    Prescription update( Prescription prescription);
}
