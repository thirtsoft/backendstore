package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Approvisionnement;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface ApprovisionnementApi {

    @PostMapping(value = APP_ROOT + "/approvisionnements/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un approvisonnement",
            notes = "Cette méthode permet d'enregistrer ou modifier un approvisionnement", response = Approvisionnement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'approvisionnement a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun approvisionnement  crée / modifié")

    })
    ResponseEntity<Approvisionnement> createApprovisionnement(@RequestBody Approvisionnement approvisionnement);


    @PutMapping(value = APP_ROOT + "/approvisionnements/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un approvisonnement par son ID",
            notes = "Cette méthode permet de modifier un approvisionnement par son ID", response = Approvisionnement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'approvisionnement a été modifié"),
            @ApiResponse(code = 400, message = "Aucun approvisionnement modifié")

    })
    ResponseEntity<Approvisionnement> updateApprovisionnement(@PathVariable(value = "id") Long ApproId, @RequestBody Approvisionnement approvisionnement);

    @GetMapping(value = APP_ROOT + "/approvisionnements/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un approvisonnement par ID",
            notes = "Cette méthode permet de chercher un approvisionnement par son ID", response = Approvisionnement.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'approvisionnement a été trouver"),
            @ApiResponse(code = 404, message = "Aucun approvisionnement n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Approvisionnement> getApprovisionnementById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @PatchMapping(value = APP_ROOT + "/approvisionnemenrs/updateStatusApproById/{id}")
    @ApiOperation(value = "Modifier un approvisonnement par son Status",
            notes = "Cette méthode permet de modifier un approvisionnement par son Status", response = Approvisionnement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le status de l'approvisionnement a été modifié")
    })
    ResponseEntity<?> updateStatusAppro(@RequestParam("status") String status, @PathVariable("id") String id);

    @PatchMapping(value = APP_ROOT + "/approvisionnemenrs/updateMontantAvanceApproById/{id}")
    @ApiOperation(value = "Modifier un approvisonnement par son ID",
            notes = "Cette méthode permet de modifier un approvisionnement par son ID", response = Approvisionnement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'approvisionnement a été modifié")
    })
    ResponseEntity<?> updateMontantAvanceAppro(@RequestParam("montantAvance") double montantAvance, @PathVariable("id") String id);

    @GetMapping(value = APP_ROOT + "/approvisionnements/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des approvisonnement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des approvisionnements", responseContainer = "List<Approvisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des approvisionnements / une liste vide")
    })
    ResponseEntity<List<Approvisionnement>> getAllApprovisionnements();

    @GetMapping(value = APP_ROOT + "/approvisionnements/allApprovisionnementOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des approvisonnement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des approvisonnement",
            responseContainer = "List<Approvisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Approvisionnements / une liste vide")
    })
    ResponseEntity<List<Approvisionnement>> getAllApprovisionnementsOrderDesc();


    @GetMapping(value = APP_ROOT + "/approvisionnements/allApprovisionnementOfLatest3Months", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des approvisonnement des 3 derniers mois",
            notes = "Cette méthode permet de chercher et renvoyer la liste des approvisonnement des trois derniers mois",
            responseContainer = "List<Approvisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Approvisionnements / une liste vide")
    })
    ResponseEntity<List<Approvisionnement>> getApprovisionnementsOfLatest3Months();


    @GetMapping(value = APP_ROOT + "/approvisionnements/findTop500OfApprovisionnementOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 500 derniers Approvisionnement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 500 derniers Approvisionnement",
            responseContainer = "List<Approvisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Approvisionnement / une liste vide")
    })
    ResponseEntity<List<Approvisionnement>> getTop500OApprovisionnementOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/approvisionnements/searchListApprovisionnementByFournisseurId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste d'approvisonnement par L'Id du fournisseur",
            notes = "Cette méthode permet de chercher et d'afficher la liste d'approvisonnement par L'Id du fournisseur",
            responseContainer = "List<Approvisionnement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'approvisionnement a été trouver"),
            @ApiResponse(code = 404, message = "Aucun approvisionnement  n'existe pas dans la BD")

    })
    ResponseEntity<List<Approvisionnement>> getListApprovisionnementByFournisseurId(@RequestParam("fourId") Long fourId);

    @DeleteMapping(value = APP_ROOT + "/approvisionnements/delete/{id}")
    @ApiOperation(value = "Supprimer un approvisonnement par son ID",
            notes = "Cette méthode permet de supprimer un approvisionnement par son ID", response = Approvisionnement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'approvisionnement a été supprimé")
    })
    ResponseEntity<?> deleteAppro(@PathVariable(value = "id") Long id);


}

