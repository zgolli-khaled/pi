package tn.esprit.pi.services;

import tn.esprit.pi.entities.Appointment;
import tn.esprit.pi.entities.Chambre;

import java.util.List;

public interface ChambreInterfaceService {
    public List<Chambre> findAll();
    public Chambre save(Chambre ch);
    public Chambre findById(Long id);
    public void delete(Long id);


}
