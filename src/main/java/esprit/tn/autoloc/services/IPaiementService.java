package esprit.tn.autoloc.services;

import esprit.tn.autoloc.dto.PaiementResponse;

import java.util.List;

/**
 * Service en lecture seule pour les paiements.
 * Les paiements sont créés en cascade via leur contrat parent.
 */
public interface IPaiementService {
    PaiementResponse findById(Long id);
    List<PaiementResponse> findAll();
}
