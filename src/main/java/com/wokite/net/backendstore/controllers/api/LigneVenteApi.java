package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneVente;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface LigneVenteApi {


    @PostMapping(value = APP_ROOT + "/ligneVentes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un LigneVente",
            notes = "Cette méthode permet d'enregistrer une LigneVente", response = LigneVente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneVente a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneVente  crée / modifié")

    })
    ResponseEntity<LigneVente> createLigneVente(@RequestBody LigneVente ligneVente);

    @PutMapping(value = APP_ROOT + "/ligneVentes/update/{lventeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une LigneVente par son ID",
            notes = "Cette méthode permet de modifier une LigneVente par son ID", response = LigneVente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneVente avec l'id ID a été modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneVente modifié avec l'id ID")

    })
    ResponseEntity<LigneVente> updateLigneVente(@PathVariable(value = "lventeId") Long lventeId, @RequestBody LigneVente ligneVente);

    @GetMapping(value = APP_ROOT + "/ligneVentes/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un LigneVente par ID",
            notes = "Cette méthode permet de chercher une Charge par son ID", response = LigneVente.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La LigneVente a été trouver"),
            @ApiResponse(code = 404, message = "Aucun LigneVente n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<LigneVente> getLigneVenteById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/ligneVentes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneVente",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneVente",
            responseContainer = "List<LigneVente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneVente / une liste vide")
    })
    ResponseEntity<List<LigneVente>> getAllLigneVentes();

    @GetMapping(value = APP_ROOT + "/ligneVentes/allLigneVenteOrderDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneVente",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneVente",
            responseContainer = "List<LigneVente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneVente / une liste vide")
    })
    ResponseEntity<List<LigneVente>> getAllLigneVenteOrderDesc();

    @GetMapping(value = APP_ROOT + "/ligneVentes/searchListLigneVentestByProduitId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneVente par Produit",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneVentes par Produit",
            responseContainer = "List<LigneVente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneVente par Produit / une liste vide")
    })
    ResponseEntity<List<LigneVente>> getListLigneVenteByProduitId(@RequestParam("prodId") Long prodId);

    @GetMapping(value = APP_ROOT + "/ligneVentes/searchListLigneVentesByVenteId/{venteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneVente par Creance",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneVentes par Vente",
            responseContainer = "List<LigneVente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneVente par Vente / une liste vide")
    })
    ResponseEntity<List<LigneVente>> getListLigneVenteByVenteId(@PathVariable("venteId") Long venteId);

    @GetMapping(value = APP_ROOT + "/ligneVentes/searchTop200OrderedItemsByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des articles les plus vendus",
            notes = "Cette méthode permet de chercher et renvoyer la liste des articles les plus vendus",
            responseContainer = "List<LigneVente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des articles les plus vendus / une liste vide")
    })
    ResponseEntity<List<LigneVente>> getTop200ItemsOrderByIdDesc();


}
