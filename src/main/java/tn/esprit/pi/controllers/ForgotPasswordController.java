package tn.esprit.pi.controllers;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.services.UserNotFoundException;
import tn.esprit.pi.services.UserServices;

import java.util.Collections;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pass")
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserServices UserService;

    @GetMapping("//forgot_password")
    public String showForgotPasswordForm() {
        return "//forgot_password";

    }

    @PostMapping("/forgot_password")
    @ResponseBody
    public ResponseEntity<?> processForgotPassword(@RequestBody String email, HttpServletRequest request) {
        if(email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Email is required"));
        }
        String token = RandomString.make(30);

        try {
            UserService.updateResetPasswordToken(token, email);
            String resetPasswordLink =  "http://localhost:4200/resetpassword?token=" + token;
            sendEmail(email, resetPasswordLink);
            return ResponseEntity.ok(Collections.singletonMap("message", "We have sent a reset password link to your email. Please check."));

        } catch (UserNotFoundException ex) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", ex.getMessage()));
        } catch (UnsupportedEncodingException | MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error while sending email"));
        }
    }




    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@shopme.com", "PI CLOUD");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = UserService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "aa";
    }

    @PostMapping("/reset_password")
    @ResponseBody
    public ResponseEntity<?> processResetPassword(@RequestBody String password,HttpServletRequest request) {
        String token = request.getParameter("token");


        User user = UserService.getByResetPasswordToken(token);
        System.out.println(user);
        if (user == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Invalid Token"));
        } else {
            UserService.updatePassword(user, password);

            return ResponseEntity.ok(Collections.singletonMap("message", "You have successfully changed your password."));
        }


}}