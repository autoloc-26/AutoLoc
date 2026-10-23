package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Vehicule;
import esprit.tn.autoloc.exception.ResourceNotFoundException;
import esprit.tn.autoloc.repository.IVehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule introuvable avec l'identifiant " + id));
    }

    @Override
    public Vehicule create(Vehicule vehicule) {
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException("Un nouveau vehicule ne doit pas avoir d'identifiant");
        }
        validate(vehicule);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule update(Long id, Vehicule vehicule) {
        Vehicule existing = findById(id);
        validate(vehicule);
        existing.setImmatriculation(vehicule.getImmatriculation());
        existing.setMarque(vehicule.getMarque());
        existing.setModele(vehicule.getModele());
        existing.setCategorie(vehicule.getCategorie());
        existing.setTarifJournalier(vehicule.getTarifJournalier());
        existing.setStatut(vehicule.getStatut());
        existing.setAgence(vehicule.getAgence());
        return vehiculeRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule introuvable avec l'identifiant " + id);
        }
        vehiculeRepository.deleteById(id);
    }

    private void validate(Vehicule vehicule) {
        if (vehicule.getImmatriculation() == null || vehicule.getImmatriculation().isBlank()) { throw new IllegalArgumentException("L'immatriculation est obligatoire"); }
        if (vehicule.getTarifJournalier() != null && vehicule.getTarifJournalier().signum() < 0) { throw new IllegalArgumentException("Le tarif journalier ne peut pas etre negatif"); }
    }
}
