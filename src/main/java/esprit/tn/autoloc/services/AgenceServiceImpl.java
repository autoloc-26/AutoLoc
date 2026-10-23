package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Agence;
import esprit.tn.autoloc.exception.ResourceNotFoundException;
import esprit.tn.autoloc.repository.IAgenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'identifiant " + id));
    }

    @Override
    public Agence create(Agence agence) {
        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException("Un nouveau agence ne doit pas avoir d'identifiant");
        }
        validate(agence);
        return agenceRepository.save(agence);
    }

    @Override
    @Transactional
    public Agence update(Long id, Agence agence) {
        Agence existing = findById(id);
        validate(agence);
        existing.setNom(agence.getNom());
        existing.setVille(agence.getVille());
        existing.setAdresse(agence.getAdresse());
        existing.setTelephone(agence.getTelephone());
        return agenceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence introuvable avec l'identifiant " + id);
        }
        agenceRepository.deleteById(id);
    }

    private void validate(Agence agence) {
        if (agence.getNom() == null || agence.getNom().isBlank()) { throw new IllegalArgumentException("Le nom de l'agence est obligatoire"); }
    }
}
