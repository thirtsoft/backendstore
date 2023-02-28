package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneAvoir;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface LigneAvoirApi {


    @PostMapping(value = APP_ROOT + "/ligneAvoirs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un LigneAvoir",
            notes = "Cette méthode permet d'enregistrer une LigneAvoir", response = LigneAvoir.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneAvoir a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneAvoir  crée / modifié")

    })
    ResponseEntity<LigneAvoir> createLigneAvoir(@RequestBody LigneAvoir ligneAvoir);

    @PutMapping(value = APP_ROOT + "/ligneAvoirs/update/{avoirId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une LigneAvoir par son ID",
            notes = "Cette méthode permet de modifier une LigneAvoir par son ID", response = LigneAvoir.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneAvoir avec l'id ID a été modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneAvoir modifié avec l'id ID")

    })
    ResponseEntity<LigneAvoir> updateLigneAvoir(@PathVariable(value = "avoirId") Long avoirId, @RequestBody LigneAvoir ligneAvoir);

    @GetMapping(value = APP_ROOT + "/ligneAvoirs/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un LigneAvoir par ID",
            notes = "Cette méthode permet de chercher une Charge par son ID", response = LigneAvoir.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La LigneAvoir a été trouver"),
            @ApiResponse(code = 404, message = "Aucun LigneAvoir n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<LigneAvoir> getLigneAvoirById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/ligneAvoirs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneAvoir",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneAvoir",
            responseContainer = "List<LigneAvoir>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneAvoir / une liste vide")
    })
    ResponseEntity<List<LigneAvoir>> getAllLigneAvoirs();

    @GetMapping(value = APP_ROOT + "/ligneAvoirs/allLigneAvoirOrderDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneAvoir",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneAvoir",
            responseContainer = "List<LigneAvoir>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneAvoir / une liste vide")
    })
    ResponseEntity<List<LigneAvoir>> getAllLigneAvoirOrderDesc();

    @GetMapping(value = APP_ROOT + "/ligneAvoirs/searchListLigneAvoirByProduitId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneAvoir par Produit",
            notes = "Cette méthode permet de chercher et renvoyer la liste des ligneAvoirs par Produit", responseContainer = "List<LigneAvoir>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneAvoir par Client / une liste vide")
    })
    ResponseEntity<List<LigneAvoir>> getListLigneAvoirByProduitId(@RequestParam("prodId") Long prodId);

    @GetMapping(value = APP_ROOT + "/ligneAvoirs/searchListLigneAvoirByAvoirId/{avoirId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneAvoir par Approvisionement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des ligneAvoirs par Avoir", responseContainer = "List<LigneAvoir>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneAvoir par Approvisionement / une liste vide")
    })
    ResponseEntity<List<LigneAvoir>> getListLigneAvoirByAvoirId(@PathVariable("avoirId") Long avoirId);


}
