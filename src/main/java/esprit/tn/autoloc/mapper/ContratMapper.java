package esprit.tn.autoloc.mapper;

import esprit.tn.autoloc.Entities.Contrat;
import esprit.tn.autoloc.Entities.Reservation;
import esprit.tn.autoloc.dto.ContratRequest;
import esprit.tn.autoloc.dto.ContratResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ContratMapper {

    private final PaiementMapper paiementMapper;

    /** Crée le contrat et ses paiements (cascade) ; la réservation est résolue par le service. */
    public Contrat toEntity(ContratRequest r, Reservation reservation) {
        Contrat c = new Contrat();
        c.setDateSignature(r.dateSignature());
        c.setMontantTotal(r.montantTotal());
        c.setValide(r.valide());
        c.setReservation(reservation);
        c.setPaiements(new ArrayList<>());
        if (r.paiements() != null) {
            r.paiements().forEach(p -> c.getPaiements().add(paiementMapper.toEntity(p, c)));
        }
        return c;
    }

    /** Seuls les attributs propres au contrat sont modifiables (les paiements restent inchangés). */
    public void updateEntity(Contrat c, ContratRequest r) {
        c.setDateSignature(r.dateSignature());
        c.setMontantTotal(r.montantTotal());
        c.setValide(r.valide());
    }

    public ContratResponse toResponse(Contrat c) {
        return new ContratResponse(c.getIdContrat(), c.getDateSignature(), c.getMontantTotal(), c.isValide(),
                c.getReservation() != null ? c.getReservation().getIdReservation() : null,
                c.getPaiements().stream().map(paiementMapper::toResponse).toList());
    }
}
