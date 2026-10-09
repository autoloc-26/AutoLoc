package esprit.tn.autoloc.repository;

import esprit.tn.autoloc.Entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeRepository extends JpaRepository<Employe, Long> {
}
