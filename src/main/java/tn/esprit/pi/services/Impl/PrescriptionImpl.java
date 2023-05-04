package tn.esprit.pi.services.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Prescription;
import tn.esprit.pi.repositories.Dossier_MedicalRepository;
import tn.esprit.pi.repositories.PrescriptionRepository;
import tn.esprit.pi.repositories.treatmentRepository;
import tn.esprit.pi.services.PrescriptionService;
import tn.esprit.pi.services.treatmentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PrescriptionImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    private final Dossier_MedicalRepository dossierMedicalRepository;

    private  final treatmentService treatmentService;
    private final tn.esprit.pi.repositories.treatmentRepository treatmentRepository;

    @Autowired
    public PrescriptionImpl(PrescriptionRepository prescriptionRepository, Dossier_MedicalRepository dossierMedicalRepository, tn.esprit.pi.services.treatmentService treatmentService,
                            treatmentRepository treatmentRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.dossierMedicalRepository = dossierMedicalRepository;

        this.treatmentService = treatmentService;
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    @Override
    public Prescription findById(Long id) {
        if (prescriptionRepository.findById(id).isPresent()) {
            return prescriptionRepository.findById(id).get();
        }
        return null;
    }
/*
    @Override

        public Prescription save(Prescription prescription) {
            // Save prescription
            //Prescription savedPrescription = prescriptionRepository.save(prescription);

            // Set prescription for each treatment and save
            for (treatment treatment : prescription.getTreatments()) {
               treatment.setPrescription(prescription)  ;
               prescription.getTreatments().add(treatmentRepository.save(treatment));
                treatmentRepository.save(treatment);
            }

            return ;
        } */


    public Prescription save(Prescription prescription) {

        return prescriptionRepository.save(prescription);
    }









    @Override
    public void delete(Long id) {
        Prescription prescription = findById(id);
        prescriptionRepository.delete(prescription);
    }

    public Prescription update(Prescription prescription) {
        Prescription existingPrescription = prescriptionRepository.findById(prescription.getId())
                .orElseThrow(() -> new EntityNotFoundException("Prescription not found"));
        existingPrescription.setTitle(prescription.getTitle());
        existingPrescription.setTreatments(prescription.getTreatments());
        return prescriptionRepository.save(existingPrescription);
    }
}
