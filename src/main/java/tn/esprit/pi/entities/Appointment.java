package tn.esprit.pi.entities;

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
    @Column(name = "ID")
    private Long idApp;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyy-mm-dd")
    @Column(name = "DATE")
    private Date dateApp;

    @Temporal(TemporalType.TIME)
    @Column(name = "DATE_DEBUT")
    private Date dateDebut;

    @Temporal(TemporalType.TIME)
    @Column(name = "DATE_FIN")
    private Date dateFin;


    @ManyToOne
    private User user;




}
