package tn.esprit.pi.services;

import tn.esprit.pi.entities.DossierMedical;

import java.util.List;

public interface DossierMedicalInterfaceService {

    public List<DossierMedical> findALL();
    public DossierMedical save(DossierMedical dossierMedical);
    public DossierMedical findById(Long id);
    public void delete(Long id);
}
