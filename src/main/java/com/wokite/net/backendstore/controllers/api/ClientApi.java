package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.Client;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Client",
            notes = "Cette méthode permet d'enregistrer un Client", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Client a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Client  crée / modifié")

    })
    ResponseEntity<Client> createClient(@RequestBody Client client);

    @PutMapping(value = APP_ROOT + "/clients/update/{catId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Client par son ID",
            notes = "Cette méthode permet de modifier un Client par son ID", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Client modifié")

    })
    ResponseEntity<Client> updateClient(@PathVariable Long catId, @RequestBody Client client);

    @GetMapping(value = APP_ROOT + "/clients/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Client par ID",
            notes = "Cette méthode permet de chercher un Client par son ID", response = Client.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Client n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long id);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Client ",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Client",
            responseContainer = "List<Client>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Clients/ une liste vide")
    })
    ResponseEntity<List<Client>>  getAllClients();

    @GetMapping(value = APP_ROOT + "/clients/allClientOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Client",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Client",
            responseContainer = "List<Client>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Client / une liste vide")
    })
    ResponseEntity<List<Client>> getAllClientsOrderDesc();

    @GetMapping(value = APP_ROOT + "/clients/countNumberOfClients")
    @ApiOperation(value = "Compter le nombre de Client",
            notes = "Cette méthode permet de retourner le nombre total un Client")
    Long getNumberOfClient();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{id}")
    @ApiOperation(value = "Supprimer un Client par son ID",
            notes = "Cette méthode permet de supprimer un Client par son ID", response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client a été supprimé")
    })
    ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id);


}
