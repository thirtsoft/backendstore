package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.Prestation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface PrestationApi {

    @PostMapping(value = APP_ROOT + "/prestations/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Prestation",
            notes = "Cette méthode permet d'enregistrer et modifier un Prestation", response = Prestation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Prestation a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Produit  crée / modifié")

    })
    ResponseEntity<Prestation> createPrestation(@RequestBody Prestation prestation);

    @PutMapping(value = APP_ROOT + "/prestations/update/{prestId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Prestation par son ID",
            notes = "Cette méthode permet de modifier une Prestation par son ID", response = Prestation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Prestation a été modifié avec succès"),
            @ApiResponse(code = 400, message = "Aucun Prestation n'a été modifié")

    })
    ResponseEntity<Prestation> updatePrestation(@PathVariable(value = "prestId") Long prestId, @RequestBody Prestation prestation);

    @GetMapping(value = APP_ROOT + "/prestations/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Prestation par ID",
            notes = "Cette méthode permet de chercher une Prestation par son ID", response = Prestation.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Produit a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Produit n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Prestation> getPrestationById(@PathVariable(value = "id") Long prestId);

    @GetMapping(value = APP_ROOT + "/prestations/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Prestation",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Prestation", responseContainer = "List<Prestation>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Prestation / une liste vide")
    })
    ResponseEntity<List<Prestation>> getAllPrestations();


    @GetMapping(value = APP_ROOT + "/prestations/allPrestationOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Prestations par ordre decroissant",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Prestation par ordre decroissant",
            responseContainer = "List<Prestation>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Prestation / une liste vide")
    })
    ResponseEntity<List<Prestation>> getAllPrestationOrderDesc();


    @GetMapping(value = APP_ROOT + "/prestations/searchSumsOfPrestationInMonth")
    @ApiOperation(value = "Compter le montant total des Prestation du moi",
            notes = "Cette méthode permet de chercher et renvoyer la montant total des Prestation du moi")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant total de Prestation du moi / une liste vide")
    })
    BigDecimal getSumsOfPrestationInMonth();

    @GetMapping(value = APP_ROOT + "/prestations/searchSumsOfPrestationInYear")
    @ApiOperation(value = "Compter le montant total des Prestation de annee",
            notes = "Cette méthode permet de chercher et renvoyer la montant total des Prestation de annees")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant total de Prestation de annees / une liste vide")
    })
    BigDecimal getSumsOfPrestationInYear();


    @DeleteMapping(value = APP_ROOT + "/prestations/delete/{id}")
    @ApiOperation(value = "Supprimer une Prestation par son ID",
            notes = "Cette méthode permet de supprimer une Prestation par son ID", response = Prestation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Prestation a été supprimé")
    })
    ResponseEntity<?> deletePrestation(@PathVariable(value = "id") Long prestId);


}
