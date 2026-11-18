package esprit.tn.autoloc.services;

import esprit.tn.autoloc.dto.AgenceRequest;
import esprit.tn.autoloc.dto.AgenceResponse;

import java.util.List;

public interface IAgenceService {
    List<AgenceResponse> findAll();

    AgenceResponse findById(Long id);

    AgenceResponse create(AgenceRequest request);

    AgenceResponse update(Long id, AgenceRequest request);

    void deleteById(Long id);
}
