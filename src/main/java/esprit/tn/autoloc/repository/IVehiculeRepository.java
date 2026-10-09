package esprit.tn.autoloc.repository;

import esprit.tn.autoloc.Entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {
}
