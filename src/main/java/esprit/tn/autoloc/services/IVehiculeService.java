package esprit.tn.autoloc.services;

import esprit.tn.autoloc.dto.VehiculeRequest;
import esprit.tn.autoloc.dto.VehiculeResponse;

import java.util.List;

public interface IVehiculeService {
    List<VehiculeResponse> findAll();

    VehiculeResponse findById(Long id);

    VehiculeResponse create(VehiculeRequest request);

    VehiculeResponse update(Long id, VehiculeRequest request);

    void deleteById(Long id);
}
