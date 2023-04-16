package tn.esprit.pi.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Dossier_Medical;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.Dossier_MedicalRepository;
import tn.esprit.pi.repositories.UserRepository;
import tn.esprit.pi.services.Dossier_MedicalService;

import java.util.List;

@Service
public class Dossier_MedicalServiceImpl implements Dossier_MedicalService {
    @Autowired
    private final Dossier_MedicalRepository dossier_MedicalRepository;
    @Autowired
    private final UserRepository userRepository;

    public Dossier_MedicalServiceImpl(Dossier_MedicalRepository dossier_MedicalRepository, UserRepository userRepository) {
        this.dossier_MedicalRepository = dossier_MedicalRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<Dossier_Medical> findAll() {
        return dossier_MedicalRepository.findAll();
    }
    @Override
    public Dossier_Medical findById(Long id) {
        if ( dossier_MedicalRepository.findById(id).isPresent()){
            return dossier_MedicalRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Dossier_Medical savewithPatient(Dossier_Medical dossier_medical) {
        User patient = dossier_medical.getUser();
        userRepository.save(patient);
        dossier_medical.setUser(patient);
        return dossier_MedicalRepository.save(dossier_medical);
    }

    @Override
    public Dossier_Medical SaveWithExistPatient(Dossier_Medical dossier_medical , Long id) {
        User patient = userRepository.findById(id).orElse(null);
        dossier_medical.setUser(patient);
        return dossier_MedicalRepository.save(dossier_medical);
    }

    @Override
    public void delete(Long id) {
        Dossier_Medical dossier_medical = findById(id);
        dossier_MedicalRepository.delete(dossier_medical);
    }

}
