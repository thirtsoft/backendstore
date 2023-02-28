package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneDevis;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface LigneDevisApi {


    @PostMapping(value = APP_ROOT + "/ligneDevis/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un LigneDevis",
            notes = "Cette méthode permet d'enregistrer une LigneDevis", response = LigneDevis.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneDevis a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneDevis  crée / modifié")

    })
    ResponseEntity<LigneDevis> createLigneDevis(@RequestBody LigneDevis ligneDevis);

    @PutMapping(value = APP_ROOT + "/ligneDevis/update/{ldevisId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une LigneDevis par son ID",
            notes = "Cette méthode permet de modifier une LigneDevis par son ID", response = LigneDevis.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneDevis avec l'id ID a été modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneDevis modifié avec l'id ID")

    })
    ResponseEntity<LigneDevis> updateLigneDevis(@PathVariable(value = "ldevisId") Long ldevisId, @RequestBody LigneDevis ligneDevis);

    @GetMapping(value = APP_ROOT + "/ligneDevis/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un LigneDevis par ID",
            notes = "Cette méthode permet de chercher une Charge par son ID", response = LigneDevis.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La LigneDevis a été trouver"),
            @ApiResponse(code = 404, message = "Aucun LigneDevis n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<LigneDevis> getLigneDevisById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/ligneDevis/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneDevis",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneDevis",
            responseContainer = "List<LigneDevis>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneDevis / une liste vide")
    })
    ResponseEntity<List<LigneDevis>> getAllLigneDeviss();

    @GetMapping(value = APP_ROOT + "/ligneDevis/allLigneDevisOrderDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneDevis",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneDevis",
            responseContainer = "List<LigneDevis>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneDevis / une liste vide")
    })
    ResponseEntity<List<LigneDevis>> getAllLigneDevisOrderDesc();

    @GetMapping(value = APP_ROOT + "/ligneDevis/searchListLigneDevisByProduitId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneDevis par Produit",
            notes = "Cette méthode permet de chercher et renvoyer la liste des ligneDeviss par Produit",
            responseContainer = "List<LigneDevis>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneDevis par Produit / une liste vide")
    })
    ResponseEntity<List<LigneDevis>> getListLigneDevisByProduitId(@RequestParam("prodId") Long prodId);

    @GetMapping(value = APP_ROOT + "/ligneDevis/searchListLigneDevisByDevisId/{devisId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneDevis par Creance",
            notes = "Cette méthode permet de chercher et renvoyer la liste des ligneDeviss par Devis",
            responseContainer = "List<LigneDevis>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneDevis par Devis / une liste vide")
    })
    ResponseEntity<List<LigneDevis>> getListLigneDevisByDevisId(@PathVariable("devisId") Long devisId);


}
