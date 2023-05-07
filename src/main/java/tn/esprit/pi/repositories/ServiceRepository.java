package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pi.entities.Service;
import org.springframework.stereotype.Repository;
@Repository
public interface ServiceRepository extends JpaRepository <Service, Long> {
}
