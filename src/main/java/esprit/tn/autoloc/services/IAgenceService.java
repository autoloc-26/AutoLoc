package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Agence;
import java.util.List;

public interface IAgenceService {
    Agence addAgence(Agence agence);
    Agence updateAgence(Agence agence);
    void deleteAgence(Long id);
    Agence getAgenceById(Long id);
    List<Agence> getAllAgences();
}
