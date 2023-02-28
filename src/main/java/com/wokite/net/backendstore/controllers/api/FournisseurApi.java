package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.Fournisseur;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "/fournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Fournisseur",
            notes = "Cette méthode permet d'enregistrer un Fournisseur", response = Fournisseur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Fournisseur a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Fournisseur  crée / modifié")

    })
    ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur);

    @PutMapping(value = APP_ROOT + "/fournisseurs/update/{catId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Fournisseur par son ID",
            notes = "Cette méthode permet de modifier un Fournisseur par son ID", response = Fournisseur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Fournisseur a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Fournisseur modifié")

    })
    ResponseEntity<Fournisseur> updateFournisseur(@PathVariable Long catId, @RequestBody Fournisseur fournisseur);

    @GetMapping(value = APP_ROOT + "/fournisseurs/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Fournisseur par ID",
            notes = "Cette méthode permet de chercher un Fournisseur par son ID", response = Fournisseur.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Fournisseur a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Fournisseur n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Fournisseur> getFournisseurById(@PathVariable(value = "id") Long id);

    @GetMapping(value = APP_ROOT + "/fournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Fournisseur ",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Fournisseur",
            responseContainer = "List<Fournisseur>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des fournisseurs/ une liste vide")
    })
    ResponseEntity<List<Fournisseur>> getAllFournisseurs();

    @GetMapping(value = APP_ROOT + "/fournisseurs/allFournisseurOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Fournisseur",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Fournisseur",
            responseContainer = "List<Fournisseur>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Fournisseur / une liste vide")
    })
    ResponseEntity<List<Fournisseur>> getAllFournisseursOrderDesc();

    @GetMapping(value = APP_ROOT + "/fournisseurs/countFournisseurs")
    @ApiOperation(value = "Compter le nombre de Fournisseur",
            notes = "Cette méthode permet de compter le nombre total de Fournisseur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre total de Fournisseur / z&ro")
    })
    Integer getNumberOfFournisseurs();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{id}")
    @ApiOperation(value = "Supprimer un Fournisseur par son ID",
            notes = "Cette méthode permet de supprimer un Fournisseur par son ID", response = Fournisseur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Fournisseur a été supprimé")
    })
    ResponseEntity<?> deleteFournisseur(@PathVariable(value = "id") Long id);


}
