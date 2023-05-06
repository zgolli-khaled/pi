package tn.esprit.pi.services;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String email) {
        super("Could not find any user with the email " + email);
    }
}
