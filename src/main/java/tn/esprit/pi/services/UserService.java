package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Role;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.RoleRepository;
import tn.esprit.pi.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository RoleRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public User createUser(User user) {
        /*Role rl = user.getRole();
        user.setRole(rl);
        RoleRepository.save(rl);*/
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setNumero(user.getNumero());
        existingUser.setBirthday(user.getBirthday());
        existingUser.setAddress(user.getAddress());
        existingUser.setAge(user.getAge());
        existingUser.setCin(user.getCin());
        existingUser.setSpecialite(user.getSpecialite());
        existingUser.setRole(user.getRole());
       /* existingUser.setPayments(user.getPayments());
        existingUser.setAppointments(user.getAppointments());
        existingUser.setChambre(user.getChambre());
        existingUser.setReclamations(user.getReclamations());
        existingUser.setPharmacie(user.getPharmacie());
        existingUser.setDossierMedical(user.getDossierMedical());
        existingUser.setPrescriptions(user.getPrescriptions());*/

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
