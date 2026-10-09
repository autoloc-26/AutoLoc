package esprit.tn.autoloc.repository;

import esprit.tn.autoloc.Entities.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAgenceRepository extends JpaRepository<Agence, Long> {
}
