package tn.esprit.pi.services;

import tn.esprit.pi.entities.Dossier_Medical;

import java.util.List;

public interface Dossier_MedicalService {
      List<Dossier_Medical> findAll();
      Dossier_Medical findById(Long id);
      Dossier_Medical savewithPatient (Dossier_Medical dossier_medical );
      Dossier_Medical SaveWithExistPatient(Dossier_Medical dossier_medical , Long id);

      void delete(Long id);

      List<Dossier_Medical>find_By_Patient_Name(String patient_name);

      List<Dossier_Medical> Search(String keyword);

}
