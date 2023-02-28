package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueVente;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface HistoriqueVenteApi {

    @PostMapping(value = APP_ROOT + "/historiqueVentes/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister HistoriqueVente",
            notes = "Cette méthode permet d'Enregister une HistoriqueVente",
            response = HistoriqueVente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "HistoriqueVente crée"),
            @ApiResponse(code = 400, message = "Aucun HistoriqueVente crée")
    })
    ResponseEntity<HistoriqueVente> createHistoriqueVente(@RequestBody HistoriqueVente historiqueVente);

    @GetMapping(value = APP_ROOT + "/historiqueVentes/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une HistoriqueVente par ID",
            notes = "Cette méthode permet de chercher un HistoriqueVente par son ID", response = HistoriqueVente.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID HistoriqueVente a été trouver"),
            @ApiResponse(code = 404, message = "Aucun HistoriqueVente n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<HistoriqueVente> getHistoriqueVenteById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/historiqueVentes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueVente",
            notes = "Cette méthode permet de chercher et renvoyer la liste des HistoriqueVente", responseContainer = "List<HistoriqueVente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueVentes / une liste vide")
    })
    ResponseEntity<List<HistoriqueVente>> getAllHistoriqueVente();

    @GetMapping(value = APP_ROOT + "/historiqueVentes/allHistoriqueVenteOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueVente",
            notes = "Cette méthode permet de chercher et renvoyer la liste des HistoriqueVentes",
            responseContainer = "List<HistoriqueVente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueVentes / une liste vide")
    })
    ResponseEntity<List<HistoriqueVente>> getAllHistoriqueVenteOrderDesc();

    @GetMapping(value = APP_ROOT + "/historiqueVentes/NumberOfHistoriqueVente", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de HistoriqueVente",
            notes = "Cette méthode permet de compter le nombre d'HistoriqueVente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'HistoriqueVente")
    })
    BigDecimal getNumberOfHistoriqueVente();

    @DeleteMapping(value = APP_ROOT + "/historiqueVentes/delete/{id}")
    @ApiOperation(value = "Supprimer une HistoriqueVente par son ID",
            notes = "Cette méthode permet de supprimer un HistoriqueVente par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La HistoriqueVente a été supprimé")
    })
    ResponseEntity<?> deleteHistoriqueVente(@PathVariable(value = "id") Long id);

}
