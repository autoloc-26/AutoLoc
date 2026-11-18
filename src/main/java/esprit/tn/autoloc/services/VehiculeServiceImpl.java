package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Agence;
import esprit.tn.autoloc.Entities.Vehicule;
import esprit.tn.autoloc.dto.VehiculeRequest;
import esprit.tn.autoloc.dto.VehiculeResponse;
import esprit.tn.autoloc.exception.ResourceNotFoundException;
import esprit.tn.autoloc.mapper.VehiculeMapper;
import esprit.tn.autoloc.repository.IAgenceRepository;
import esprit.tn.autoloc.repository.IVehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResponse> findAll() {
        return vehiculeRepository.findAll().stream().map(vehiculeMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VehiculeResponse findById(Long id) {
        return vehiculeMapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional
    public VehiculeResponse create(VehiculeRequest request) {
        Vehicule vehicule = vehiculeMapper.toEntity(request, resolveAgence(request.agenceId()));
        return vehiculeMapper.toResponse(vehiculeRepository.save(vehicule));
    }

    @Override
    @Transactional
    public VehiculeResponse update(Long id, VehiculeRequest request) {
        Vehicule existing = getOrThrow(id);
        vehiculeMapper.updateEntity(existing, request, resolveAgence(request.agenceId()));
        return vehiculeMapper.toResponse(vehiculeRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule introuvable avec l'identifiant " + id);
        }
        vehiculeRepository.deleteById(id);
    }

    private Vehicule getOrThrow(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule introuvable avec l'identifiant " + id));
    }

    private Agence resolveAgence(Long agenceId) {
        if (agenceId == null) {
            return null;
        }
        return agenceRepository.findById(agenceId)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'identifiant " + agenceId));
    }
}
