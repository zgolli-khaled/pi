package tn.esprit.pi.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter

public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PRES")
    private Long idPres;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyy-mm-dd")
    @Column(name = "DATE")
    private Date date;

    @Column(name ="DESCRIPTION")
    private String description;
}
