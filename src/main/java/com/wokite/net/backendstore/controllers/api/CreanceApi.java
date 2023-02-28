package com.wokite.net.backendstore.controllers.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wokite.net.backendstore.models.Creance;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface CreanceApi {

    @PostMapping(value = APP_ROOT + "/creances/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Creance",
            notes = "Cette méthode permet d'enregistrer et modifier une Creance", response = Creance.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Creance a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Creance n'a été crée / modifié")

    })
    ResponseEntity<Creance> createCreance(@RequestBody Creance creance /* @RequestParam Long id */);


    @GetMapping(value = APP_ROOT + "/creances/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Creance par ID",
            notes = "Cette méthode permet de chercher un Creance par son ID", response = Creance.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creance a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Creance n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Creance> getCreanceById(@PathVariable(value = "id") Long id);

    @PutMapping(value = APP_ROOT + "/creances/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Creance par son ID",
            notes = "Cette méthode permet de modifier une Creance par son ID", response = Creance.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Creance a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Creance n'a été crée / modifié")

    })
    ResponseEntity<Creance> updateCreance(@PathVariable(value = "id") Long id, @RequestBody Creance creance) throws Exception;

    @PostMapping(value = APP_ROOT + "/creances/updateStatus")
    @ApiOperation(value = "Modifier un Creance par son status",
            notes = "Cette méthode permet de modifier un Creance par son status", response = Creance.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creance a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Creance modifié")

    })
    ResponseEntity<Boolean> updateStatus(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/creances/updateStatusCreance", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Creance par status",
            notes = "Cette méthode permet de modifier une Creance par son status", response = Creance.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le status de la  Creance a été modifié"),
            @ApiResponse(code = 404, message = "Aucun le status de la Creance n'a pas été modifié")

    })
    ResponseEntity<Boolean> updateStatusOfCreance(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/creances/setCreanceStatusById/{id}")
    @ApiOperation(value = "Modifier une Creance par status",
            notes = "Cette méthode permet de modifier une Creance par son status", response = Creance.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le status de la  Creance a été modifié"),
            @ApiResponse(code = 404, message = "Aucun le status de la Creance n'a pas été modifié")

    })
    ResponseEntity<Creance> updateStatusOfCreanceById(@PathVariable("id") Long id, @RequestBody Creance creance);


    @PatchMapping(value = APP_ROOT + "/creances/setCreanceOnlyStatus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier le status d'une Creance par son ID",
            notes = "Cette méthode permet de modifier le status d'une Creance par son ID", response = Creance.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le status de la Creance a été modifié"),
            @ApiResponse(code = 400, message = "Aucun status de Creance n'a été modifié")

    })
    ResponseEntity<?> updateOnlyStatusOfCreance(@RequestParam("status") String status, @PathVariable("id") String id);

    @PatchMapping(value = APP_ROOT + "/creances/setCreanceOnlyAvanceCreance/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier l'avance d'une Creance par son ID",
            notes = "Cette méthode permet de modifier la somme de creance avancée par son ID", response = Creance.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'avance de la Creance a été modifié"),
            @ApiResponse(code = 400, message = "Aucun avance de Creance n'a été modifié")

    })
    ResponseEntity<?> updateOnlyAvanceOfCreance(@RequestParam("avanceCreance") double avanceCreance, @PathVariable("id") String id);

    @GetMapping(value = APP_ROOT + "/creances/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des creances ",
            notes = "Cette méthode permet de chercher et renvoyer la liste des creances",
            responseContainer = "List<Creance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Creance/ une liste vide")
    })
    ResponseEntity<List<Creance>> getAllCreances();

    @GetMapping(value = APP_ROOT + "/creances/allCreanceOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Creance",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Creance",
            responseContainer = "List<Creance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Creance / une liste vide")
    })
    ResponseEntity<List<Creance>> getAllCreancesOrderDesc();

    @GetMapping(value = APP_ROOT + "/creances/allCreanceOf3LatestMonths", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Creances des trois derniers mois",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Creance des trois derniers mois",
            responseContainer = "List<Creance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Creance / une liste vide")
    })
    ResponseEntity<List<Creance>> getAllCreanceOf3LatestMonths();

    @GetMapping(value = APP_ROOT + "/creances/findTop500OfCreanceOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 500 derniers Creances",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 500 derniers Creance",
            responseContainer = "List<Creance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Creance / une liste vide")
    })
    ResponseEntity<List<Creance>> getTop500OCreanceOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/creances/searchListCreanceByClientId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Creance d'un Client",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Creance d'un Client",
            responseContainer = "List<Creance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Creance par client / une liste vide")
    })
    ResponseEntity<List<Creance>> getAllCreanceByClientId(@RequestParam("clientId") Long clientId);

    @GetMapping(value = APP_ROOT + "/creances/searchListCreanceByClientIdAndStatus/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Creances encours d'un Client",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Creance encours d'un Client", responseContainer = "List<Creance>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Creance par client / une liste vide")
    })
    ResponseEntity<List<Creance>> getAllCreanceByClientIdAndStatus(@PathVariable(name = "clientId") Long clientId);

    @GetMapping(value = APP_ROOT + "/creances/NumberOfCreances")
    @ApiOperation(value = "Compter le nombre de Creance",
            notes = "Cette méthode permet de compter le nombre total de Creance"
    )
    int getNumberOfCreances();

    @GetMapping(value = APP_ROOT + "/creances/SumNumbersOfCreances")
    @ApiOperation(value = "Additionner le total de Creance",
            notes = "Cette méthode permet de faire la somme total de Creance"
    )
    BigDecimal getSumTotalOfCreances();

    @GetMapping(value = APP_ROOT + "/creances/SumTotalOfCreancesInYear")
    @ApiOperation(value = "La somme total de creance par années",
            notes = "Cette méthode permet de donner le montant total de creance par années"
    )
    BigDecimal getSumTotalOfCreanceInYear();

    @GetMapping(value = APP_ROOT + "/creances/sumTotalOfCreancePeerMonth")
    @ApiOperation(value = "Additionner la somme des Creance par mois",
            notes = "Cette méthode permet de faire la somme total des Creance par mois"
    )
    List<?> sumTotalOfCreancesPeerMonth();

    @DeleteMapping(value = APP_ROOT + "/creances/delete/{id}")
    @ApiOperation(value = "Supprimer un Creance par son ID",
            notes = "Cette méthode permet de supprimer un Creance par son ID", response = Creance.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creance a été supprimé")
    })
    ResponseEntity<?> deleteCreance(@PathVariable(value = "id") Long id);


}
