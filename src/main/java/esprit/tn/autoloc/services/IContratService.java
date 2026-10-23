package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Contrat;
import java.util.List;

public interface IContratService {
    Contrat create(Contrat contrat);
    Contrat update(Long id, Contrat contrat);
    void deleteById(Long id);
    Contrat findById(Long id);
    List<Contrat> findAll();
}
