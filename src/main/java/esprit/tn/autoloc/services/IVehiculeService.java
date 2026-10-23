package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Vehicule;

import java.util.List;

public interface IVehiculeService {
    List<Vehicule> findAll();

    Vehicule findById(Long id);

    Vehicule create(Vehicule vehicule);

    Vehicule update(Long id, Vehicule vehicule);

    void deleteById(Long id);
}
