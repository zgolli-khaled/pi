package tn.esprit.pi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.pi.entities.Enumeration.service_hosp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dossier_Medical implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motif_admission;
    @Enumerated(EnumType.STRING)
    private service_hosp Service_hospitalier;

    private String antecedents;

    @Temporal(TemporalType.TIMESTAMP )
    private Date datecreation;

    private String attachment_file;



    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    private List<Prescription> prescriptions;



    public String toSearchString() {
        return motif_admission + " " + antecedents + " " + datecreation + " " +getUser().getFirst_name()+""
                +getUser().getCIN()+getUser().getAdress()+getUser().getLast_name()+getUser().getPhone_nbr() /* other fields */ ;
    }

}
