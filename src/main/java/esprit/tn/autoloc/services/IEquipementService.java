package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Equipement;
import java.util.List;

public interface IEquipementService {
    Equipement addEquipement(Equipement equipement);
    Equipement updateEquipement(Equipement equipement);
    void deleteEquipement(Long id);
    Equipement getEquipementById(Long id);
    List<Equipement> getAllEquipements();
}
