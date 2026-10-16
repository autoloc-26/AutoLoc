package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Paiement;
import java.util.List;

public interface IPaiementService {
    Paiement addPaiement(Paiement paiement);
    Paiement updatePaiement(Paiement paiement);
    void deletePaiement(Long id);
    Paiement getPaiementById(Long id);
    List<Paiement> getAllPaiements();
}
