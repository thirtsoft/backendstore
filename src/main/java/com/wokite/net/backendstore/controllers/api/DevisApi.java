package com.wokite.net.backendstore.controllers.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wokite.net.backendstore.models.Devis;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface DevisApi {

    @PostMapping(value = APP_ROOT + "/devis/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Devis",
            notes = "Cette méthode permet d'enregistrer et modifier une Devis", response = Devis.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Devis a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Devis n'a été crée / modifié")

    })
    ResponseEntity<Devis> createDevis(@RequestBody Devis devis, @RequestParam Long id);

    @GetMapping(value = APP_ROOT + "/devis/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Devis par ID",
            notes = "Cette méthode permet de chercher un Devis par son ID", response = Devis.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devis a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Devis n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Devis> getDevisById(@PathVariable(value = "id") Long id);

    @PutMapping(value = APP_ROOT + "/devis/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Devis par son ID",
            notes = "Cette méthode permet de modifier une Devis par son ID", response = Devis.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Devis a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Devis n'a été crée / modifié")

    })
    ResponseEntity<Devis> updateDevis(@PathVariable(value = "id") Long id, @RequestBody Devis devis) throws Exception;

    @GetMapping(value = APP_ROOT + "/devis/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des devis ",
            notes = "Cette méthode permet de chercher et renvoyer la liste des devis",
            responseContainer = "List<Devis>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Devis/ une liste vide")
    })
    ResponseEntity<List<Devis>> getAllDevis();

    @GetMapping(value = APP_ROOT + "/devis/allDevisOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Devis",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Devis",
            responseContainer = "List<Devis>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Devis / une liste vide")
    })
    ResponseEntity<List<Devis>> getAllDevisOrderDesc();

    @GetMapping(value = APP_ROOT + "/devis/searchListDevisByClientId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Devis d'un Client",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Devis d'un Client",
            responseContainer = "List<Devis>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Devis par client / une liste vide")
    })
    ResponseEntity<List<Devis>> getDevisByClientId(@RequestParam("clientId") Long clientId);

    @GetMapping(value = APP_ROOT + "/devis/NumberOfDevis")
    @ApiOperation(value = "Compter le nombre de Devis",
            notes = "Cette méthode permet de compter le nombre total de Devis"
    )
    int getNumberOfDevis();

    @GetMapping(value = APP_ROOT + "/devis/NumbersOfdevis")
    @ApiOperation(value = "Compter le nombre de Devis",
            notes = "Cette méthode permet de compter le nombre total de Devis"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Nombre total de Devis / zéro")
    })
    BigDecimal getNumbersOfDevis();

    @GetMapping(value = APP_ROOT + "/devis/searchNumberOfDevisPeerMonth")
    @ApiOperation(value = "Renvoi le nombre de devis par mois",
            notes = "Cette méthode permet de compter et renvoyer le nombre de Devis par mois")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Nombre de Devis par mois / zéro")
    })
    List<?> getNumberTotalOfDevisPeerMonth();

    @GetMapping(value = APP_ROOT + "/devis/sumTotalOfDevisPeerMonth")
    @ApiOperation(value = "Additionner la somme des Devis par mois",
            notes = "Cette méthode permet de faire la somme total des Devis par mois"
    )
    List<?> sumTotalOfDevisPeerMonth();

    @DeleteMapping(value = APP_ROOT + "/devis/delete/{id}")
    @ApiOperation(value = "Supprimer un Devis par son ID",
            notes = "Cette méthode permet de supprimer un Devis par son ID", response = Devis.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Devis a été supprimé")
    })
    ResponseEntity<?> deleteDevis(@PathVariable(value = "id") Long id);


}
