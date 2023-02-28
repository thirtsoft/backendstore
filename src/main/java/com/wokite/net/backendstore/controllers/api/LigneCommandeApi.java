package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneCommande;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface LigneCommandeApi {


    @PostMapping(value = APP_ROOT + "/ligneCommandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un LigneCommande",
            notes = "Cette méthode permet d'enregistrer une LigneCommande", response = LigneCommande.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneCommande a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneCommande  crée / modifié")

    })
    ResponseEntity<LigneCommande> createLigneCommande(@RequestBody LigneCommande ligneCommande);

    @PutMapping(value = APP_ROOT + "/ligneCommandes/update/{lcomId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une LigneCommande par son ID",
            notes = "Cette méthode permet de modifier une LigneCommande par son ID", response = LigneCommande.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La LigneCommande avec l'id ID a été modifié"),
            @ApiResponse(code = 400, message = "Aucun LigneCommande modifié avec l'id ID")

    })
    ResponseEntity<LigneCommande> updateLigneCommande(@PathVariable(value = "lcomId") Long lcomId, @RequestBody LigneCommande ligneCommande);

    @GetMapping(value = APP_ROOT + "/ligneCommandes/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un LigneCommande par ID",
            notes = "Cette méthode permet de chercher une Charge par son ID", response = LigneCommande.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La LigneCommande a été trouver"),
            @ApiResponse(code = 404, message = "Aucun LigneCommande n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<LigneCommande> getLigneCommandeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/ligneCommandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCommande",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneCommande",
            responseContainer = "List<LigneCommande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCommande / une liste vide")
    })
    ResponseEntity<List<LigneCommande>> getAllLigneCommandes();

    @GetMapping(value = APP_ROOT + "/ligneCommandes/allLigneCommandeOrderDesc",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCommande",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneCommande",
            responseContainer = "List<LigneCommande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCommande / une liste vide")
    })
    ResponseEntity<List<LigneCommande>> getAllLigneCommandeOrderDesc();

    @GetMapping(value = APP_ROOT + "/ligneCommandes/searchListLigneCommandeByProduitId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCommande par Produit",
            notes = "Cette méthode permet de chercher et renvoyer la liste des ligneCommandes par Produit",
            responseContainer = "List<LigneCommande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCommande par Client / une liste vide")
    })
    ResponseEntity<List<LigneCommande>> getListLigneCommandeByProduitId(@RequestParam("prodId") Long prodId);

    @GetMapping(value = APP_ROOT + "/ligneCommandes/searchListLigneCommandeByCommandeId/{comId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCommande par Commande",
            notes = "Cette méthode permet de chercher et renvoyer la liste des ligneCommandes par Commande",
            responseContainer = "List<LigneCommande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCommande par Approvisionement / une liste vide")
    })
    ResponseEntity<List<LigneCommande>> getListLigneCommandeByCommandeId(@PathVariable("comId") Long comId);


}
