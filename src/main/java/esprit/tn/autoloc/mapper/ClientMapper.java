package esprit.tn.autoloc.mapper;

import esprit.tn.autoloc.Entities.Client;
import esprit.tn.autoloc.dto.ClientRequest;
import esprit.tn.autoloc.dto.ClientResponse;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequest r) {
        Client c = new Client();
        updateEntity(c, r);
        return c;
    }

    public void updateEntity(Client c, ClientRequest r) {
        c.setNom(r.nom());
        c.setPrenom(r.prenom());
        c.setEmail(r.email());
        c.setTelephone(r.telephone());
        c.setNumPermis(r.numPermis());
        c.setDateInscription(r.dateInscription());
    }

    public ClientResponse toResponse(Client c) {
        return new ClientResponse(c.getIdClient(), c.getNom(), c.getPrenom(), c.getEmail(),
                c.getTelephone(), c.getNumPermis(), c.getDateInscription());
    }
}
