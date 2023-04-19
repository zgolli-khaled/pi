package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Appointment;
import tn.esprit.pi.entities.Chambre;
import tn.esprit.pi.repositories.ChambreRepository;

import java.util.List;

@Service
public class ChambreService implements ChambreInterfaceService{
    @Autowired
    private ChambreRepository chambreRepository;


    @Override
    public List<Chambre> findAll() {
        return  chambreRepository.findAll();
    }

    @Override
    public Chambre save(Chambre ch) {
        chambreRepository.save(ch);
        return ch;
    }

    @Override
    public Chambre findById(Long id) {
        if(chambreRepository.findById(id).isPresent()){
            return  chambreRepository.findById(id).get();

        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Chambre chambre = findById(id);
        chambreRepository.delete(chambre);
    }




}
