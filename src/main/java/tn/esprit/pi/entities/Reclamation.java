package tn.esprit.pi.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_REC")
    private Long idRec;

    @Column(name ="NOM")
    private String nom;

    @Column(name ="DESCRIPTION")
    private String description;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyy-mm-dd")
    @Column(name = "DATE")
    private Date date;
}
