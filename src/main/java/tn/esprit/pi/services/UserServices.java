package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println(user);

        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }


    public User getUserByResetPasswordToken(String token) throws UserNotFoundException {
        User user = userRepository.findByResetPasswordToken(token);
        if (user == null) {
            throw new UserNotFoundException("User not found with the given token");
        }
        return user;
    }
}
