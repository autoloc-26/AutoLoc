package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Contrat;
import esprit.tn.autoloc.Entities.Paiement;
import esprit.tn.autoloc.exception.ResourceNotFoundException;
import esprit.tn.autoloc.repository.IContratRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    @Override
    public Contrat create(Contrat contrat) {
        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException("Un nouveau contrat ne doit pas avoir d'identifiant");
        }
        if (contrat.getMontantTotal() != null && contrat.getMontantTotal().signum() < 0) {
            throw new IllegalArgumentException("Le montant total ne peut pas être négatif");
        }
        // Gestion de la cascade pour les paiements
        if (contrat.getPaiements() != null) {
            for (Paiement paiement : contrat.getPaiements()) {
                paiement.setContrat(contrat);
            }
        }
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat update(Long id, Contrat contrat) {
        Contrat existing = contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable avec l'identifiant " + id));
        
        // Mise à jour des seuls attributs modifiables
        existing.setDateSignature(contrat.getDateSignature());
        existing.setMontantTotal(contrat.getMontantTotal());
        existing.setValide(contrat.isValide());
        
        if (contrat.getMontantTotal() != null && contrat.getMontantTotal().signum() < 0) {
            throw new IllegalArgumentException("Le montant total ne peut pas être négatif");
        }
        
        return contratRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat introuvable avec l'identifiant " + id);
        }
        contratRepository.deleteById(id);
    }

    @Override
    public Contrat findById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }
}
