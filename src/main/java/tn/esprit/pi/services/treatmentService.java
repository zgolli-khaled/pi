package tn.esprit.pi.services;

import tn.esprit.pi.entities.treatment;

import java.util.List;

public interface treatmentService {
    List<treatment> findAll();
    treatment findById(Long id);
    treatment save ( treatment treatment );
    void delete(Long id);


}
