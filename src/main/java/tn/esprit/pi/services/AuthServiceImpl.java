/*package tn.esprit.pi.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.payload.response.MessageResponse;
import tn.esprit.pi.repositories.RoleRepository;
import tn.esprit.pi.repositories.UserRepository;
import tn.esprit.pi.security.jwt.JwtUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;
@Service
public class AuthServiceImpl implements IAuthService{


    private static final Logger LOG = LogManager.getLogger(AuthServiceImpl.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public ResponseEntity<MessageResponse> resetPassword(String email) {
        return null;
    }

    @Override
    public ResponseEntity<MessageResponse> envoyerMail(String email) {
        Optional<User> u = userRepository.findByEmail(email);

        if (u.isPresent()) {
            // Send confirmation code to email
            try {
                LOG.info(MyConstants.SENDING_MAIL);
                sendEmail(email, u.get().getCode());
                LOG.info(MyConstants.MAIL_SENT);
            } catch (MessagingException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body(new MessageResponse(MyConstants.FAIL_TO_SEND_MAIL));
            }
        }

        return ResponseEntity
                .ok(new MessageResponse(MyConstants.CODE_SEND_SUCCESS));

    }




    public void sendPassword(String destination, String code) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true); // true indicates
        // multipart message
        helper.setSubject(MyConstants.MAIL_SUBJECT_RESET);
        helper.setTo(destination);
        helper.setText("<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px;"
                        + " background-color: #f2f3f8;\" leftmargin=\"0\"><table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\" "
                        + "       style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">"
                        + "        <tr>" + "            <td>"
                        + "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\""
                        + "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">"
                        + "                    <tr>" + "                        <td style=\"height:80px;\">&nbsp;</td>"
                        + "                    </tr>" + "                    <tr>"
                        + "                        <td style=\"height:20px;\">&nbsp;</td>" + "                    </tr>"
                        + "                    <tr>" + "                        <td>"
                        + "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\""
                        + "                               style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">"
                        + "                                <tr>"
                        + "                                    <td style=\"height:40px;\">&nbsp;</td>"
                        + "                                </tr>" + "                                <tr>"
                        + "                                    <td style=\"padding:0 35px;\">"
                        + "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">"
                        + "Vous avez demandé de réinitialiser votre mot de passe </h1>"
                        + "                                        <span"
                        + "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>"
                        + "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">"
                        + "Nous ne pouvons pas simplement vous envoyer votre ancien mot de passe."
                        + " Un nouveau mot de passe pour votre compte a été généré pour vous."
                        + " Vous devez l'utiliser pour vous connecter la prochaine fois."
                        + "                                        </p>"
                        + "                                        <a style=\"background:#1eb7e6;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">"
                        + code + "</a>" + "                                    </td>" + "                                </tr>"
                        + "                                <tr>"
                        + "                                    <td style=\"height:40px;\">&nbsp;</td>"
                        + "                                </tr>" + "                            </table>"
                        + "                        </td>" + "                    <tr>"
                        + "                        <td style=\"height:20px;\">&nbsp;</td>" + "                        </tr>"
                        + "                    <tr>" + "                        <td style=\"text-align:center;\">"
                        + "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">"
                        + "<strong>Site Web de recrutement</strong> &copy; <strong>www.bct.gov.tn</strong></p>" + "                        </td>"
                        + "                    </tr>" + "                    <tr>"
                        + "                        <td style=\"height:80px;\">&nbsp;</td>" + "                    </tr>"
                        + "                </table>" + "            </td>" + "        </tr>" + "    </table>" + "</body>",
                true); // true indicates html
        javaMailSender.send(message);
    }

    public String generateCode(String type) {
        // create a string of uppercase and lowercase characters and numbers
        String upperAlphabet = MyConstants.ALPHABET;
        String numbers = MyConstants.NUMBERS;

        String alphaNumeric = upperAlphabet + numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 0;
        if (type.equals(MyConstants.CODE_TYPE_CONFIRM)) {
            length = 6;
        } else if (type.equals(MyConstants.CODE_TYPE_RESET)) {
            length = 12;
        }

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        return sb.toString();
    }
    public void sendEmail(String destination, String code) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true); // true indicates
        // multipart message
        helper.setSubject(MyConstants.MAIL_SUBJECT_CONFIRM);
        helper.setTo(destination);
        helper.setText("<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px;"
                        + " background-color: #f2f3f8;\" leftmargin=\"0\"><table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\" "
                        + "       style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">"
                        + "        <tr>" + "            <td>"
                        + "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\""
                        + "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">"
                        + "                    <tr>" + "                        <td style=\"height:80px;\">&nbsp;</td>"
                        + "                    </tr>" + "                    <tr>"
                        + "                        <td style=\"height:20px;\">&nbsp;</td>" + "                    </tr>"
                        + "                    <tr>" + "                        <td>"
                        + "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\""
                        + "                               style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">"
                        + "                                <tr>"
                        + "                                    <td style=\"height:40px;\">&nbsp;</td>"
                        + "                                </tr>" + "                                <tr>"
                        + "                                    <td style=\"padding:0 35px;\">"
                        + "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">"
                        + "Confirmation de votre inscription</h1>" + "                                        <span"
                        + "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>"
                        + "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">"
                        + "Pour terminer votre inscription, veuillez utiliser ce code de confirmation lors de votre prochaine connexion.</p>"
                        + "                                        <a style=\"background:#1eb7e6;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">"
                        + code + "</a>" + "                                    </td>" + "                                </tr>"
                        + "                                <tr>"
                        + "                                    <td style=\"height:40px;\">&nbsp;</td>"
                        + "                                </tr>" + "                            </table>"
                        + "                        </td>" + "                    <tr>"
                        + "                        <td style=\"height:20px;\">&nbsp;</td>" + "                        </tr>"
                        + "                    <tr>" + "                        <td style=\"text-align:center;\">"
                        + "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">"
                        + "<strong>Site Web de recrutement</strong> &copy; <strong>www.bct.g</strong></p>" + "                        </td>"
                        + "                    </tr>" + "                    <tr>"
                        + "                        <td style=\"height:80px;\">&nbsp;</td>" + "                    </tr>"
                        + "                </table>" + "            </td>" + "        </tr>" + "    </table>" + "</body>",
                true); // true indicates html
        javaMailSender.send(message);
    }

}*/
