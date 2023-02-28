package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueCreance;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface HistoriqueCreanceApi {

    @PostMapping(value = APP_ROOT + "/historiqueCreances/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister historique HistoriqueCreance",
            notes = "Cette méthode permet d'Enregister une historique d'HistoriqueCreance",
            responseContainer = "List<HistoriqueCreance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueCreance / une liste vide")
    })
    ResponseEntity<HistoriqueCreance> createHistoriqueCreance(@RequestBody HistoriqueCreance historiqueCreance);

    @GetMapping(value = APP_ROOT + "/historiqueCreances/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une HistoriqueCreance par ID",
            notes = "Cette méthode permet de chercher un HistoriqueCreance par son ID", response = HistoriqueCreance.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID HistoriqueCreance a été trouver"),
            @ApiResponse(code = 404, message = "Aucun HistoriqueCreance n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<HistoriqueCreance> getHistoriqueCreanceById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;


    @GetMapping(value = APP_ROOT + "/historiqueCreances/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueCreance",
            notes = "Cette méthode permet de chercher et renvoyer la liste des HistoriqueCreance",
            responseContainer = "List<HistoriqueCreance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueCreance / une liste vide")
    })
    ResponseEntity<List<HistoriqueCreance>> getAllHistoriqueCreances();

    @GetMapping(value = APP_ROOT + "/historiqueCreances/allHistoriqueCreanceOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueCreance",
            notes = "Cette méthode permet de chercher et renvoyer la liste des HistoriqueCreance",
            responseContainer = "List<HistoriqueCreance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des historiqueLogins / une liste vide")
    })
    ResponseEntity<List<HistoriqueCreance>> getAllHistoriqueCreancesOrderDesc();

    @GetMapping(value = APP_ROOT + "/historiqueCreances/NumberOfHistoriqueCreance", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de HistoriqueCreance",
            notes = "Cette méthode permet de compter le nombre Historique Creance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'Historique Creance / une liste vide")
    })
    BigDecimal getNumberOfHistoriqueCreances();

    @DeleteMapping(value = APP_ROOT + "/historiqueCreances/delete/{id}")
    @ApiOperation(value = "Supprimer une HistoriqueCreance par son ID",
            notes = "Cette méthode permet de supprimer un HistoriqueCreance par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La HistoriqueCreance a été supprimé")
    })
    ResponseEntity<?> deleteHistoriqueCreance(@PathVariable(value = "id") Long id);

}
