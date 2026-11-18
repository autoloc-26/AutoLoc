package esprit.tn.autoloc.services;

import esprit.tn.autoloc.dto.ContratRequest;
import esprit.tn.autoloc.dto.ContratResponse;

import java.util.List;

public interface IContratService {
    List<ContratResponse> findAll();

    ContratResponse findById(Long id);

    ContratResponse create(ContratRequest request);

    ContratResponse update(Long id, ContratRequest request);

    void deleteById(Long id);
}
