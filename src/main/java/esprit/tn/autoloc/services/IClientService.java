package esprit.tn.autoloc.services;

import esprit.tn.autoloc.dto.ClientRequest;
import esprit.tn.autoloc.dto.ClientResponse;

import java.util.List;

public interface IClientService {
    List<ClientResponse> findAll();

    ClientResponse findById(Long id);

    ClientResponse create(ClientRequest request);

    ClientResponse update(Long id, ClientRequest request);

    void deleteById(Long id);
}
