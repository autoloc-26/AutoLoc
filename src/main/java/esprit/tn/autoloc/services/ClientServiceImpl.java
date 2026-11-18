package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Client;
import esprit.tn.autoloc.dto.ClientRequest;
import esprit.tn.autoloc.dto.ClientResponse;
import esprit.tn.autoloc.exception.ResourceNotFoundException;
import esprit.tn.autoloc.mapper.ClientMapper;
import esprit.tn.autoloc.repository.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> findAll() {
        return clientRepository.findAll().stream().map(clientMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponse findById(Long id) {
        return clientMapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional
    public ClientResponse create(ClientRequest request) {
        return clientMapper.toResponse(clientRepository.save(clientMapper.toEntity(request)));
    }

    @Override
    @Transactional
    public ClientResponse update(Long id, ClientRequest request) {
        Client existing = getOrThrow(id);
        clientMapper.updateEntity(existing, request);
        return clientMapper.toResponse(clientRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client introuvable avec l'identifiant " + id);
        }
        clientRepository.deleteById(id);
    }

    private Client getOrThrow(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable avec l'identifiant " + id));
    }
}
