package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Client;

import java.util.List;

public interface IClientService {
    List<Client> findAll();

    Client findById(Long id);

    Client create(Client client);

    Client update(Long id, Client client);

    void deleteById(Long id);
}