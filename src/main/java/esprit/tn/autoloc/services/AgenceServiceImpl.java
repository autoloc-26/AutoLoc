package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Agence;
import esprit.tn.autoloc.dto.AgenceRequest;
import esprit.tn.autoloc.dto.AgenceResponse;
import esprit.tn.autoloc.exception.ResourceNotFoundException;
import esprit.tn.autoloc.mapper.AgenceMapper;
import esprit.tn.autoloc.repository.IAgenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AgenceResponse> findAll() {
        return agenceRepository.findAll().stream().map(agenceMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AgenceResponse findById(Long id) {
        return agenceMapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional
    public AgenceResponse create(AgenceRequest request) {
        return agenceMapper.toResponse(agenceRepository.save(agenceMapper.toEntity(request)));
    }

    @Override
    @Transactional
    public AgenceResponse update(Long id, AgenceRequest request) {
        Agence existing = getOrThrow(id);
        agenceMapper.updateEntity(existing, request);
        return agenceMapper.toResponse(agenceRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence introuvable avec l'identifiant " + id);
        }
        agenceRepository.deleteById(id);
    }

    private Agence getOrThrow(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'identifiant " + id));
    }
}
