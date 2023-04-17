package tn.esprit.pi.services;

import tn.esprit.pi.entities.Prescription;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> findAll();
    Prescription findById(Long id);
    Prescription save (Prescription prescription , Long id );
    void delete(Long id);
}
