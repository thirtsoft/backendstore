package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneCreance;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface LigneCreanceApi {


    @PostMapping(value = APP_ROOT + "/ligneCreances/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un LigneCreance",
            notes = "Cette méthode permet d'enregistrer une LigneCreance", response = LigneCreance.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneCreance a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneCreance  crée / modifié")

    })
    ResponseEntity<LigneCreance> createLigneCreance(@RequestBody LigneCreance ligneCreance);

    @PutMapping(value = APP_ROOT + "/ligneCreances/update/{lcreanceId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une LigneCreance par son ID",
            notes = "Cette méthode permet de modifier une LigneCreance par son ID", response = LigneCreance.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneCreance avec l'id ID a été modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneCreance modifié avec l'id ID")

    })
    ResponseEntity<LigneCreance> updateLigneCreance(@PathVariable(value = "lcreanceId") Long lcreanceId, @RequestBody LigneCreance ligneCreance);

    @GetMapping(value = APP_ROOT + "/ligneCreances/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un LigneCreance par ID",
            notes = "Cette méthode permet de chercher une Charge par son ID", response = LigneCreance.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La LigneCreance a été trouver"),
            @ApiResponse(code = 404, message = "Aucun LigneCreance n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<LigneCreance> getLigneCreanceById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/ligneCreances/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCreance",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneCreance",
            responseContainer = "List<LigneCreance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCreance / une liste vide")
    })
    ResponseEntity<List<LigneCreance>> getAllLigneCreances();

    @GetMapping(value = APP_ROOT + "/ligneCreances/allLigneCreanceOrderDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCreance",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneCreance",
            responseContainer = "List<LigneCreance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCreance / une liste vide")
    })
    ResponseEntity<List<LigneCreance>> getAllLigneCreanceOrderDesc();

    @GetMapping(value = APP_ROOT + "/ligneCreances/searchListLigneCreanceByProduitId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCreance par Produit",
            notes = "Cette méthode permet de chercher et renvoyer la liste des ligneCreances par Produit",
            responseContainer = "List<LigneCreance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCreance par Produit / une liste vide")
    })
    ResponseEntity<List<LigneCreance>> getListLigneCreanceByProduitId(@RequestParam("prodId") Long prodId);

    @GetMapping(value = APP_ROOT + "/ligneCreances/searchListLigneCreanceByCreanceId/{creanceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCreance par Creance",
            notes = "Cette méthode permet de chercher et renvoyer la liste des ligneCreances par Creance",
            responseContainer = "List<LigneCreance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCreance par Creance / une liste vide")
    })
    ResponseEntity<List<LigneCreance>> getListLigneCreanceByCreanceId(@PathVariable("creanceId") Long creanceId);


}
