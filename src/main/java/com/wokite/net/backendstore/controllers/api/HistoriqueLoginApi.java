package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueCreance;
import com.wokite.net.backendstore.models.HistoriqueLogin;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface HistoriqueLoginApi {

    @PostMapping(value = APP_ROOT + "/historiqueLogins/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister un HistoriqueLogin",
            notes = "Cette méthode permet d'Enregister une HistoriqueLogin",
            response = HistoriqueLogin.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "HistoriqueLogin crée"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueLogin crée")
    })
    ResponseEntity<HistoriqueLogin> createHistoriqueLogin(@RequestBody HistoriqueLogin historiqueLogin);

    @GetMapping(value = APP_ROOT + "/historiqueLogins/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une HistoriqueLogin par ID",
            notes = "Cette méthode permet de chercher un HistoriqueLogin par son ID", response = HistoriqueLogin.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID HistoriqueLogin a été trouver"),
            @ApiResponse(code = 404, message = "Aucun HistoriqueLogin n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<HistoriqueLogin> getHistoriqueLoginById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/historiqueLogins/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueLogin",
            notes = "Cette méthode permet de chercher et renvoyer la liste des HistoriqueLogin", responseContainer = "List<HistoriqueLogin>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des historiqueLogins / une liste vide")
    })
    ResponseEntity<List<HistoriqueLogin>> getAllHistoriqueLogin();

    @GetMapping(value = APP_ROOT + "/historiqueLogins/allHistoriqueLoginOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueLogin",
            notes = "Cette méthode permet de chercher et renvoyer la liste des historiqueLogins", responseContainer = "List<HistoriqueLogin>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des historiqueLogins / une liste vide")
    })
    ResponseEntity<List<HistoriqueLogin>> getAllHistoriqueLoginOrderDesc();

    @GetMapping(value = APP_ROOT + "/historiqueLogins/NumberOfHistoriqueLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de HistoriqueLogin",
            notes = "Cette méthode permet de compter le nombre d'historique de connexion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'historique de connexion / une liste vide")
    })
    BigDecimal getNumberOfHistoriqueLogin();

    @DeleteMapping(value = APP_ROOT + "/historiqueLogins/delete/{id}")
    @ApiOperation(value = "Supprimer une HistoriqueLogin par son ID",
            notes = "Cette méthode permet de supprimer un HistoriqueLogin par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La HistoriqueLogin a été supprimé")
    })
    ResponseEntity<?> deleteHistoriqueLogin(@PathVariable(value = "id") Long id);

}
