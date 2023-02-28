package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueDepense;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface HistoriqueDepenseApi {

    @PostMapping(value = APP_ROOT + "/historiqueCharges/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister historique HistoriqueCharge",
            notes = "Cette méthode permet d'Enregister une HistoriqueCharge",
            response = HistoriqueDepense.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "HistoriqueCharge crée / une liste vide"),
            @ApiResponse(code = 404, message = "Aucun HistoriqueLogin crée")

    })
    ResponseEntity<HistoriqueDepense> createHistoriqueCharge(@RequestBody HistoriqueDepense historiqueDepense);

    @GetMapping(value = APP_ROOT + "/historiqueCharges/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une HistoriqueCharge par ID",
            notes = "Cette méthode permet de chercher un HistoriqueAppro par son ID", response = HistoriqueDepense.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID HistoriqueCharge a été trouver"),
            @ApiResponse(code = 404, message = "Aucun HistoriqueLogin n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<HistoriqueDepense> getHistoriqueChargeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/historiqueCharges/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueCharge",
            notes = "Cette méthode permet de chercher et renvoyer la liste des HistoriqueCharge", responseContainer = "List<HistoriqueCharge>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueCharge / une liste vide")
    })
    ResponseEntity<List<HistoriqueDepense>> getAllHistoriqueCharges();

    @GetMapping(value = APP_ROOT + "/historiqueCharges/allHistoriqueChargeOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueCharge",
            notes = "Cette méthode permet de chercher et renvoyer la liste des historiqueLogins", responseContainer = "List<HistoriqueCharge>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueCharge / une liste vide")
    })
    ResponseEntity<List<HistoriqueDepense>> getAllHistoriqueChargesOrderDesc();

    @GetMapping(value = APP_ROOT + "/historiqueCharges/NumberOfHistoriqueCharge", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de HistoriqueCharge",
            notes = "Cette méthode permet de compter le nombre HistoriqueCharge")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'historique de connexion / une liste vide")
    })
    BigDecimal getNumbersOfHistoriqueCharges();


    @DeleteMapping(value = APP_ROOT + "/historiqueCharges/delete/{id}")
    @ApiOperation(value = "Supprimer une HistoriqueCharge par son ID",
            notes = "Cette méthode permet de supprimer un HistoriqueCharge par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La HistoriqueLogin a été supprimé")
    })
    ResponseEntity<?> deleteHistoriqueCharge(@PathVariable(value = "id") Long id);

}
