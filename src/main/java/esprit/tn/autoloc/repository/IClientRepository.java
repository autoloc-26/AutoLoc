package esprit.tn.autoloc.repository;

import esprit.tn.autoloc.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {
}
