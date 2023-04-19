package tn.esprit.pi.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idRole;

    @Enumerated(EnumType.STRING)

    private TypeRole role;




    @OneToMany(mappedBy="role" )
    private Set<User> Users;


}
