package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueCommande;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface HistoriqueCommandeApi {

    @PostMapping(value = APP_ROOT + "/historiqueCommandes/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister Historique Commande",
            notes = "Cette méthode permet d'Enregister une Historique Commande",
            responseContainer = "List<HistoriqueCommande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des approvisionnements / une liste vide")
    })
    ResponseEntity<HistoriqueCommande> createHistoriqueCommande(@RequestBody HistoriqueCommande historiqueCommande);


    @GetMapping(value = APP_ROOT + "/historiqueCommandes/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une HistoriqueCommande par ID",
            notes = "Cette méthode permet de chercher un HistoriqueCommande par son ID", response = HistoriqueCommande.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID HistoriqueCommande a été trouver"),
            @ApiResponse(code = 404, message = "Aucun HistoriqueCommande n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<HistoriqueCommande> getHistoriqueCommandeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;


    @GetMapping(value = APP_ROOT + "/historiqueCommandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueCommande",
            notes = "Cette méthode permet de chercher et renvoyer la liste des HistoriqueCommande",
            responseContainer = "List<HistoriqueCommande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des historiqueLogins / une liste vide")
    })
    ResponseEntity<List<HistoriqueCommande>> getAllHistoriqueCommandes();

    @GetMapping(value = APP_ROOT + "/historiqueCommandes/allHistoriqueCommandeOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueCommande",
            notes = "Cette méthode permet de chercher et renvoyer la liste des historiqueLogins",
            responseContainer = "List<HistoriqueCommande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueCommande / une liste vide")
    })
    ResponseEntity<List<HistoriqueCommande>> getAllHistoriqueCommandesOrderDesc();

    @GetMapping(value = APP_ROOT + "/historiqueCommandes/NumberOfHistoriqueCommande", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de HistoriqueCommande",
            notes = "Cette méthode permet de compter le nombre d'historique de connexion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'historique de connexion / une liste vide")
    })
    BigDecimal getNumberOfHistoriqueCommande();

    @DeleteMapping(value = APP_ROOT + "/historiqueCommandes/delete/{id}")
    @ApiOperation(value = "Supprimer une HistoriqueCommande par son ID",
            notes = "Cette méthode permet de supprimer un HistoriqueCommande par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La HistoriqueLogin a été supprimé")
    })
    ResponseEntity<?> deleteHistoriqueCommande(@PathVariable(value = "id") Long id);

}
