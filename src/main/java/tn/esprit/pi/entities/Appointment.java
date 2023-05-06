package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApp;



    private String dateApp;

private String state;



    private String heureDebut;




    private String description;

    private String cin;


    @ManyToOne
    @JoinColumn(name="id_user")
   // @JsonBackReference

    private User user;




}
