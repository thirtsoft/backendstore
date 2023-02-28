package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueAvoir;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface HistoriqueAvoirApi {

    @PostMapping(value = APP_ROOT + "/historiqueAvoirs/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregister historique Avoir",
            notes = "Cette méthode permet d'Enregister une historique d'avoir",
            responseContainer = "List<HistoriqueAvoir>")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "L'historique avoirs a été crée"),
            @ApiResponse(code = 400, message = "L'historique avoirs n'a pas été crée")
    })
    ResponseEntity<HistoriqueAvoir> createHistoriqueAvoir(HistoriqueAvoir historiqueAvoir);

    @GetMapping(value = APP_ROOT + "/historiqueAvoirs/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une HistoriqueAvoir par ID",
            notes = "Cette méthode permet de chercher un HistoriqueAvoir par son ID", response = HistoriqueAvoir.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID HistoriqueAvoir a été trouver"),
            @ApiResponse(code = 404, message = "Aucun HistoriqueAvoir n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<HistoriqueAvoir> getHistoriqueAvoirById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/historiqueAvoirs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueAvoir",
            notes = "Cette méthode permet de chercher et renvoyer la liste des HistoriqueAvoir", responseContainer = "List<HistoriqueAvoir>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des historiqueAvoirs / une liste vide")
    })
    ResponseEntity<List<HistoriqueAvoir>> getAllHistoriqueAvoirs();

    @GetMapping(value = APP_ROOT + "/historiqueAvoirs/allHistoriqueAvoirOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des HistoriqueAvoir",
            notes = "Cette méthode permet de chercher et renvoyer la liste des historiqueAvoirs",
            responseContainer = "List<HistoriqueAvoir>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des HistoriqueAvoir / une liste vide")
    })
    ResponseEntity<List<HistoriqueAvoir>> getAllHistoriqueAvoirOrderDesc();

    @GetMapping(value = APP_ROOT + "/historiqueAvoirs/NumberOfHistoriqueAvoir", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de HistoriqueAvoir",
            notes = "Cette méthode permet de compter le nombre d'historique d'avoir")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre d'historique d'avoirs / une liste vide")
    })
    BigDecimal getNumberOfHistoriqueAvoirs();

    @DeleteMapping(value = APP_ROOT + "/historiqueAvoirs/delete/{id}")
    @ApiOperation(value = "Supprimer une HistoriqueAvoir par son ID",
            notes = "Cette méthode permet de supprimer un HistoriqueAvoir par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La HistoriqueLogin a été supprimé")
    })
    ResponseEntity<?> deleteHistoriqueAvoir(@PathVariable(value = "id") Long id);

}
