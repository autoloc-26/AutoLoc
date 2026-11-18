package esprit.tn.autoloc.mapper;

import esprit.tn.autoloc.Entities.Agence;
import esprit.tn.autoloc.dto.AgenceRequest;
import esprit.tn.autoloc.dto.AgenceResponse;
import org.springframework.stereotype.Component;

@Component
public class AgenceMapper {

    public Agence toEntity(AgenceRequest r) {
        Agence a = new Agence();
        updateEntity(a, r);
        return a;
    }

    public void updateEntity(Agence a, AgenceRequest r) {
        a.setNom(r.nom());
        a.setVille(r.ville());
        a.setAdresse(r.adresse());
        a.setTelephone(r.telephone());
    }

    public AgenceResponse toResponse(Agence a) {
        return new AgenceResponse(a.getIdAgence(), a.getNom(), a.getVille(), a.getAdresse(), a.getTelephone());
    }
}
