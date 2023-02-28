package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client saveClient(Client client);

    Client updateClient(Long id, Client client);

    Client updateClientByEmail(String email, String id);

    Optional<Client> findClientById(Long id);

    List<Client> findAllClient();

    List<Client> findAllClientsOrderDesc();

    List<Object[]> ListClientGroupByRaisonSocial();

    Long countNumberOfClient();

    void deleteClient(Long id);


}
