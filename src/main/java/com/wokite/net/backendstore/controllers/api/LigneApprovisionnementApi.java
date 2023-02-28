package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneApprovisionnement;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface LigneApprovisionnementApi {


    @PostMapping(value = APP_ROOT + "/ligneApprovisionnements/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un LigneApprovisionnement",
            notes = "Cette méthode permet d'enregistrer une LigneApprovisionnement", response = LigneApprovisionnement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneApprovisionnement a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneApprovisionnement  crée / modifié")

    })
    ResponseEntity<LigneApprovisionnement> createLigneApprovisionnement(@RequestBody LigneApprovisionnement ligneApprovisionnement);

    @PutMapping(value = APP_ROOT + "/ligneApprovisionnements/update/{lApproId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une LigneApprovisionnement par son ID",
            notes = "Cette méthode permet de modifier une LigneApprovisionnement par son ID", response = LigneApprovisionnement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneApprovisionnement avec l'id ID a été modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneApprovisionnement modifié avec l'id ID")

    })
    ResponseEntity<LigneApprovisionnement> updateLigneApprovisionnement(@PathVariable(value = "lApproId") Long lApproId, @RequestBody LigneApprovisionnement ligneApprovisionnement);

    @GetMapping(value = APP_ROOT + "/ligneApprovisionnements/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un LigneApprovisionnement par ID",
            notes = "Cette méthode permet de chercher une Charge par son ID", response = LigneApprovisionnement.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La LigneApprovisionnement a été trouver"),
            @ApiResponse(code = 404, message = "Aucun LigneApprovisionnement n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<LigneApprovisionnement> getLigneApprovisionnementById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/ligneApprovisionnements/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneApprovisionnement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneApprovisionnement",
            responseContainer = "List<LigneApprovisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneApprovisionnement / une liste vide")
    })
    ResponseEntity<List<LigneApprovisionnement>> getAllLigneApprovisionnements();

    @GetMapping(value = APP_ROOT + "/ligneApprovisionnements/allLigneApprovisionnementOrderDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneApprovisionnement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneApprovisionnement",
            responseContainer = "List<LigneApprovisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneApprovisionnement / une liste vide")
    })
    ResponseEntity<List<LigneApprovisionnement>> getAllLigneApprovisionnementOrderDesc();

    @GetMapping(value = APP_ROOT + "/ligneApprovisionnements/searchListLigneApprovisionnementByProduitId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneApprovisionnement par Produit",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneApprovisionnements par Produit", responseContainer = "List<LigneApprovisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneApprovisionnement par Client / une liste vide")
    })
    ResponseEntity<List<LigneApprovisionnement>> getListLigneApprovisionnementByProduitId(@RequestParam("prodId") Long prodId);

    @GetMapping(value = APP_ROOT + "/ligneApprovisionnements/searchListLigneApproByApprovisionnementId/{approId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneApprovisionnement par Approvisionement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneApprovisionnements par Approvisionement", responseContainer = "List<LigneApprovisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneApprovisionnement par Approvisionement / une liste vide")
    })
    ResponseEntity<List<LigneApprovisionnement>> getListLigneApprovisionnementByApprovisionnementId(@PathVariable("approId") Long approId);


}
