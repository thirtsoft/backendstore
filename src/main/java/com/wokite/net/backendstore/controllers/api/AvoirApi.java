package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Avoir;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface AvoirApi {

    @PostMapping(value = APP_ROOT + "/avoirs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un avoir",
            notes = "Cette méthode permet d'enregistrer un avoir", response = Avoir.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'avoir a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun avoir  crée / modifié")

    })
    ResponseEntity<Avoir> createAvoir(@Valid @RequestBody Avoir avoir);

    @PutMapping(value = APP_ROOT + "/avoirs/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un avoir par son ID",
            notes = "Cette méthode permet de modifier un avoir par son ID", response = Avoir.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'avoir a été modifié"),
            @ApiResponse(code = 400, message = "Aucun avoir modifié")
    })
    ResponseEntity<Avoir> updateAvoir(@PathVariable Long id, @RequestBody Avoir avoir);

    @GetMapping(value = APP_ROOT + "/avoirs/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un avoir par ID",
            notes = "Cette méthode permet de chercher un avoir par son ID", response = Avoir.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Avoir a été trouver"),
            @ApiResponse(code = 404, message = "Aucun avoir n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Avoir> getAvoirById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/avoirs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des avoirs",
            notes = "Cette méthode permet de chercher et renvoyer la liste des avoirs", responseContainer = "List<Avoir>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des avoirs / une liste vide")
    })
    ResponseEntity<List<Avoir>>  getAllAvoirs();

    @GetMapping(value = APP_ROOT + "/avoirs/allAvoirOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Avoir",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Avoir",
            responseContainer = "List<Avoir>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Avoirs / une liste vide")
    })
    ResponseEntity<List<Avoir>> getAllAvoirOrderDesc();

    @GetMapping(value = APP_ROOT + "/avoirs/searchListAvoirsByFournisseurId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Avoir par ID Fournisseur",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Avoir par ID Fournisseur",
            responseContainer = "List<Avoir>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Avoirs / une liste vide")
    })
    ResponseEntity<List<Avoir>> getListOfAvoirByFournisseurId(@RequestParam("four") Long fourId);


    @DeleteMapping(value = APP_ROOT + "/avoirs/delete/{id}")
    @ApiOperation(value = "Supprimer un avoir par son ID",
            notes = "Cette méthode permet de supprimer un avoir par son ID", response = Avoir.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Avoir a été supprimé")
    })
    ResponseEntity<Object> deleteAvoir(@PathVariable(value = "id") Long id);


}
