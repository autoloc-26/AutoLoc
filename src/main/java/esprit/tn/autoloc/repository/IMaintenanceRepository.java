package esprit.tn.autoloc.repository;

import esprit.tn.autoloc.Entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
