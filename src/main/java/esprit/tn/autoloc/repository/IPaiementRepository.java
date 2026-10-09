package esprit.tn.autoloc.repository;

import esprit.tn.autoloc.Entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
}
