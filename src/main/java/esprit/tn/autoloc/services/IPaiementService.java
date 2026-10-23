package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Paiement;
import java.util.List;

/**
 * Service en lecture seule pour les paiements.
 * Les paiements sont créés en cascade via leur contrat parent.
 */
public interface IPaiementService {
    Paiement findById(Long id);
    List<Paiement> findAll();
}
