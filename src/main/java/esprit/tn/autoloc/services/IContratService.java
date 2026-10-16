package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Contrat;
import java.util.List;

public interface IContratService {
    Contrat addContrat(Contrat contrat);
    Contrat updateContrat(Contrat contrat);
    void deleteContrat(Long id);
    Contrat getContratById(Long id);
    List<Contrat> getAllContrats();
}
