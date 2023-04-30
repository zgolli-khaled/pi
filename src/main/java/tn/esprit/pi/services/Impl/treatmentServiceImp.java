package tn.esprit.pi.services.Impl;

import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Prescription;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.entities.treatment;
import tn.esprit.pi.repositories.PrescriptionRepository;
import tn.esprit.pi.repositories.treatmentRepository;
import tn.esprit.pi.services.treatmentService;

import java.util.List;
@Service
public class treatmentServiceImp implements treatmentService {
    public treatmentRepository treatmentRepository;
    private final PrescriptionRepository prescriptionRepository;

    public treatmentServiceImp(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public List<treatment> findAll() {

        return treatmentRepository.findAll() ;
    }

    @Override
    public treatment findById(Long id) {

        if (treatmentRepository.findById(id).isPresent()) {
            return treatmentRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public treatment save(treatment treatment) {

        return treatmentRepository.save(treatment);
    }

    @Override
    public void delete(Long id) {
        treatment tr = findById(id);
        treatmentRepository.delete(tr);

    }
}
