package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Contrat;
import esprit.tn.autoloc.Entities.Reservation;
import esprit.tn.autoloc.dto.ContratRequest;
import esprit.tn.autoloc.dto.ContratResponse;
import esprit.tn.autoloc.exception.ResourceNotFoundException;
import esprit.tn.autoloc.mapper.ContratMapper;
import esprit.tn.autoloc.repository.IContratRepository;
import esprit.tn.autoloc.repository.IReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final IReservationRepository reservationRepository;
    private final ContratMapper contratMapper;

    @Override
    @Transactional
    public ContratResponse create(ContratRequest request) {
        Contrat contrat = contratMapper.toEntity(request, resolveReservation(request.reservationId()));
        return contratMapper.toResponse(contratRepository.save(contrat));
    }

    @Override
    @Transactional
    public ContratResponse update(Long id, ContratRequest request) {
        Contrat existing = getOrThrow(id);
        contratMapper.updateEntity(existing, request);
        return contratMapper.toResponse(contratRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat introuvable avec l'identifiant " + id);
        }
        contratRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ContratResponse findById(Long id) {
        return contratMapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratResponse> findAll() {
        return contratRepository.findAll().stream().map(contratMapper::toResponse).toList();
    }

    private Contrat getOrThrow(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable avec l'identifiant " + id));
    }

    private Reservation resolveReservation(Long reservationId) {
        if (reservationId == null) {
            return null;
        }
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation introuvable avec l'identifiant " + reservationId));
    }
}
