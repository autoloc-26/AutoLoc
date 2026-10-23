package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Paiement;
import esprit.tn.autoloc.exception.ResourceNotFoundException;
import esprit.tn.autoloc.repository.IPaiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service en lecture seule pour les paiements.
 * Les paiements sont créés uniquement via la cascade du contrat parent.
 */
@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement findById(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }
}
