package tn.esprit.pi.services;

import org.springframework.http.ResponseEntity;
import tn.esprit.pi.payload.response.MessageResponse;

public interface IAuthService {
    public ResponseEntity<MessageResponse> resetPassword(String email);
    public ResponseEntity<MessageResponse> envoyerMail(String email);



}
