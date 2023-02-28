package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueApprovisionnement;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface HistoriqueApprovisionnementApi {

    @PostMapping(value = APP_ROOT + "/historiqueApprovisionnements/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister historique Appro",
            notes = "Cette méthode permet d'Enregister une historique d'approvisionnement",
            responseContainer = "List<HistoriqueApprovisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des approvisionnements / une liste vide")
    })
    ResponseEntity<HistoriqueApprovisionnement> createApprovisionnement(HistoriqueApprovisionnement historiqueApprovisionnement);

    @GetMapping(value = APP_ROOT + "/historiqueApprovisionnements/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une HistoriqueAppro par ID",
            notes = "Cette méthode permet de chercher un HistoriqueAppro par son ID", response = HistoriqueApprovisionnement.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID HistoriqueLogin a été trouver"),
            @ApiResponse(code = 404, message = "Aucun HistoriqueLogin n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<HistoriqueApprovisionnement> getHistoriqueApprovisionnementById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/historiqueApprovisionnements/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueApprovisionnement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des HistoriqueApprovisionnement", responseContainer = "List<HistoriqueApprovisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des historiqueLogins / une liste vide")
    })
    ResponseEntity<List<HistoriqueApprovisionnement>> getAllHistoriqueApprovisionnements();

    @GetMapping(value = APP_ROOT + "/historiqueApprovisionnements/allHistoriqueApprovisionnementOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueApprovisionnement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des historiqueLogins", responseContainer = "List<HistoriqueApprovisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des historiqueLogins / une liste vide")
    })
    ResponseEntity<List<HistoriqueApprovisionnement>> getAllHistoriqueApprovisionnementsOrderDesc();

    @GetMapping(value = APP_ROOT + "/historiqueApprovisionnements/NumberOfHistoriqueApprovisionnement", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de HistoriqueApprovisionnement",
            notes = "Cette méthode permet de compter le nombre d'historique de connexion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'historique de connexion / une liste vide")
    })
    BigDecimal getNumberOfHistoriqueApprovisionnement();

    @DeleteMapping(value = APP_ROOT + "/historiqueApprovisionnements/delete/{id}")
    @ApiOperation(value = "Supprimer une HistoriqueApprovisionnement par son ID",
            notes = "Cette méthode permet de supprimer un HistoriqueApprovisionnement par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La HistoriqueLogin a été supprimé")
    })
    ResponseEntity<?> deleteHistoriqueApprovisionnement(@PathVariable(value = "id") Long id);


}
