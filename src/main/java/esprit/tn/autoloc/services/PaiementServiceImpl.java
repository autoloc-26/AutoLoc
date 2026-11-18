package esprit.tn.autoloc.services;

import esprit.tn.autoloc.dto.PaiementResponse;
import esprit.tn.autoloc.exception.ResourceNotFoundException;
import esprit.tn.autoloc.mapper.PaiementMapper;
import esprit.tn.autoloc.repository.IPaiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service en lecture seule pour les paiements.
 * Les paiements sont créés uniquement via la cascade du contrat parent.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;
    private final PaiementMapper paiementMapper;

    @Override
    public PaiementResponse findById(Long id) {
        return paiementRepository.findById(id)
                .map(paiementMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement introuvable avec l'identifiant " + id));
    }

    @Override
    public List<PaiementResponse> findAll() {
        return paiementRepository.findAll().stream().map(paiementMapper::toResponse).toList();
    }
}
