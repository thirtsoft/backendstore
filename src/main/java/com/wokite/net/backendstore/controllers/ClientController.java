package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.ClientApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Client;
import com.wokite.net.backendstore.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ClientController implements ClientApi {

    @Autowired
    private ClientService clientService;

    @Override
    public ResponseEntity<Client> createClient(Client client) {
        Client clientResult = clientService.saveClient(client);
        return new ResponseEntity<>(clientResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Client> updateClient(Long catId, Client client) {
        client.setId(catId);
        Client clientResult = clientService.saveClient(client);
        return new ResponseEntity<>(clientResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Client> getClientById(Long id) {
        Client client = clientService.findClientById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client that id is" + id + "not found"));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clientList = clientService.findAllClient();
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Client>> getAllClientsOrderDesc() {
        List<Client> clientList = clientService.findAllClientsOrderDesc();
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @Override
    public Long getNumberOfClient() {
        return clientService.countNumberOfClient();
    }

    @Override
    public ResponseEntity<?> deleteClient(Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
