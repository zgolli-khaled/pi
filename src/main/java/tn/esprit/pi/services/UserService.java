package tn.esprit.pi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.UserRepository;

import java.util.List;

@Service
public class UserService  implements UserInterfaceService{


    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findALL() {
        return  userRepository.findAll();
    }
}
