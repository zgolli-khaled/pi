package tn.esprit.pi.controllers;

import java.util.*;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Role;
import tn.esprit.pi.entities.TypeRole;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.payload.request.LoginRequest;
import tn.esprit.pi.payload.request.SignupRequest;
import tn.esprit.pi.payload.response.JwtResponse;
import tn.esprit.pi.payload.response.MessageResponse;
import tn.esprit.pi.repositories.RoleRepository;
import tn.esprit.pi.repositories.UserRepository;
import tn.esprit.pi.security.jwt.JwtUtils;
import tn.esprit.pi.services.IAuthService;

import tn.esprit.pi.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    AuthenticationManager authenticationManager;
   /* @Autowired
    IAuthService authService;*/
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        System.out.println(loginRequest.getUsername() + " " + loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getNom())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        String confirmationCode = generateConfirmationCode();

      /*  SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpRequest.getEmail());
        message.setSubject("Account Confirmation");
        message.setText("Your confirmation code is: " + confirmationCode);
        emailSender.send(message);*/
        // Create new user's account
        User user = new User(signUpRequest.getNom(),
                signUpRequest.getEmail(), signUpRequest.getCin(), signUpRequest.getNumero(),
                signUpRequest.getAddress(), signUpRequest.getPrenom(), signUpRequest.getAge(), signUpRequest.getBirthday(),
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


            strRoles.forEach(role -> {
                System.out.println(signUpRequest.getRole());
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(TypeRole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "GESTIONNAIRE":
                        Role GESTIONNAIRErole = roleRepository.findByName(TypeRole.GESTIONNAIRE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(GESTIONNAIRErole);

                        break;
                    case "PATIENT":
                        Role PATIENTrole = roleRepository.findByName(TypeRole.PATIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(PATIENTrole);

                        break;

                    case "MEDECIN":
                        Role MEDECINrole = roleRepository.findByName(TypeRole.MEDECIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(MEDECINrole);

                        break;

                   /* default:
                        Role userRole = roleRepository.findByName(TypeRole.PATIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);*/
                }
            });


        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    private String generateConfirmationCode() {
        // Generate a random alphanumeric string
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 6;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

 /*   @PostMapping("/resetpw")
    public ResponseEntity<MessageResponse> resetPassword(@RequestBody String email) {
        return authService.resetPassword(email);
    }*/

}
