package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Client;
import com.wokite.net.backendstore.repository.ClientRepository;
import com.wokite.net.backendstore.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired private ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client N° not found");
        }
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            throw new ResourceNotFoundException("Client N° " + id + "not found");
        }
        Client clientResult = optionalClient.get();
        clientResult.setRaisonSocial(client.getRaisonSocial());
        clientResult.setAddress(client.getAddress());
        clientResult.setTelephone(client.getTelephone());
        clientResult.setMobile(client.getMobile());
        clientResult.setEmail(client.getEmail());

        return clientRepository.save(clientResult);
    }

    @Override
    public Client updateClientByEmail(String email, String id) {
        Optional<Client> originalClient = clientRepository.findById(Long.valueOf(id));
        Client client = originalClient.get();
        client.setEmail(email);
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findClientById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client N° " + id + "not found");
        }
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> findAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> findAllClientsOrderDesc() {
        return clientRepository.findByOrderByIdDesc();
    }


    @Override
    public List<Object[]> ListClientGroupByRaisonSocial() {
        return clientRepository.ListClientGroupByRaisonSocial();
    }

    @Override
    public Long countNumberOfClient() {
        return clientRepository.count();
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found");
        }
        clientRepository.deleteById(id);
    }
}
