package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Agence;

import java.util.List;

public interface IAgenceService {
    List<Agence> findAll();

    Agence findById(Long id);

    Agence create(Agence agence);

    Agence update(Long id, Agence agence);

    void deleteById(Long id);
}
