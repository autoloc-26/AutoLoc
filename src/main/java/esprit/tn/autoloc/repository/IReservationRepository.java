package esprit.tn.autoloc.repository;

import esprit.tn.autoloc.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
