package tn.esprit.pi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.DossierMedical;
import tn.esprit.pi.repositories.DossierMedicalRepository;

import java.util.List;

@Service
public class DossierMedicalService implements DossierMedicalInterfaceService {

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Override
    public List<DossierMedical> findALL() {
        return dossierMedicalRepository.findAll();
    }

    @Override
    public DossierMedical save(DossierMedical dossierMedical) {
        dossierMedicalRepository.save(dossierMedical);
        return dossierMedical;
    }

    @Override
    public DossierMedical findById(Long id) {
        if(dossierMedicalRepository.findById(id).isPresent()){
            return dossierMedicalRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        DossierMedical doss = findById(id);
        dossierMedicalRepository.delete(doss);

    }
}
