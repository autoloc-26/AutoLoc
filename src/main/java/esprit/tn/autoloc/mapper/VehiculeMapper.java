package esprit.tn.autoloc.mapper;

import esprit.tn.autoloc.Entities.Agence;
import esprit.tn.autoloc.Entities.StatutVehicule;
import esprit.tn.autoloc.Entities.Vehicule;
import esprit.tn.autoloc.dto.VehiculeRequest;
import esprit.tn.autoloc.dto.VehiculeResponse;
import org.springframework.stereotype.Component;

@Component
public class VehiculeMapper {

    /** L'agence est résolue par le service à partir de agenceId. */
    public Vehicule toEntity(VehiculeRequest r, Agence agence) {
        Vehicule v = new Vehicule();
        updateEntity(v, r, agence);
        return v;
    }

    public void updateEntity(Vehicule v, VehiculeRequest r, Agence agence) {
        v.setImmatriculation(r.immatriculation());
        v.setMarque(r.marque());
        v.setModele(r.modele());
        v.setCategorie(r.categorie());
        v.setTarifJournalier(r.tarifJournalier());
        v.setStatut(r.statut() != null ? r.statut() : StatutVehicule.DISPONIBLE);
        v.setAgence(agence);
    }

    public VehiculeResponse toResponse(Vehicule v) {
        Agence a = v.getAgence();
        return new VehiculeResponse(v.getIdVehicule(), v.getImmatriculation(), v.getMarque(), v.getModele(),
                v.getCategorie(), v.getTarifJournalier(), v.getStatut(),
                a != null ? a.getIdAgence() : null, a != null ? a.getNom() : null);
    }
}
