package esprit.tn.autoloc.mapper;

import esprit.tn.autoloc.Entities.Contrat;
import esprit.tn.autoloc.Entities.Paiement;
import esprit.tn.autoloc.dto.PaiementRequest;
import esprit.tn.autoloc.dto.PaiementResponse;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {

    public Paiement toEntity(PaiementRequest r, Contrat contrat) {
        Paiement p = new Paiement();
        p.setMontant(r.montant());
        p.setDatePaiement(r.datePaiement());
        p.setModePaiement(r.modePaiement());
        p.setContrat(contrat);
        return p;
    }

    public PaiementResponse toResponse(Paiement p) {
        Contrat c = p.getContrat();
        return new PaiementResponse(p.getIdPaiement(), p.getMontant(), p.getDatePaiement(),
                p.getModePaiement(), c != null ? c.getIdContrat() : null);
    }
}
