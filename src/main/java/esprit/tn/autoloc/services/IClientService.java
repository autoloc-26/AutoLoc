package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Client;
import java.util.List;

public interface IClientService {
    Client addClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Long id);
    Client getClientById(Long id);
    List<Client> getAllClients();
}
