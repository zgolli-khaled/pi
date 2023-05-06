package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Appointment;
import tn.esprit.pi.entities.Chambre;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ChambreRepository;
import tn.esprit.pi.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreService implements ChambreInterfaceService{
    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailSenderService emailSenderService;

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

    @Override
    public void affecterchambre(Long ch, Long id) {
        Optional<Chambre> chambreOptional = chambreRepository.findById(ch);
        if (chambreOptional.isPresent()){
            Chambre chambre = chambreOptional.get();
            if(chambre.getUser()== null) {
                User user = userRepository.findById(id).orElse(null);
                chambre.setUser(user);
                chambreRepository.save(chambre);
                String email = user.getAddress();
                emailSenderService.sendEmail(email , "votre chambre :  " + chambre.getNum() +"\n"
                        + "l'etage de chambre "+ chambre.getEtage() ,"affectation chambre");
            }else{
                System.out.println("erreuuuur!!!!");
            }
        }

    }


}
