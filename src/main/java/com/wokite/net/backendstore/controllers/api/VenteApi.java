package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Vente;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface VenteApi {

    @PostMapping(value = APP_ROOT + "/ventes/savevente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Vente",
            notes = "Cette méthode permet d'enregistrer une Vente", response = Vente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Vente a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Vente  crée / modifié")

    })
    ResponseEntity<Vente> saveVente(@RequestBody Vente vente);

    @PostMapping(value = APP_ROOT + "/ventes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Vente",
            notes = "Cette méthode permet d'enregistrer une Vente", response = Vente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Vente a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Vente  crée / modifié")

    })
    ResponseEntity<Vente> createVente(@RequestBody Vente vente, @RequestParam Long id);

    @PostMapping(value = APP_ROOT + "/ventes/venteWithbarCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Vente avec lecteur code-barres",
            notes = "Cette méthode permet d'enregistrer une Vente en utilisant un lecteur code-barres", response = Vente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Vente a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Vente  crée / modifié")

    })
    ResponseEntity<Vente> createVenteWithBarcode(@RequestBody Vente vente, @RequestParam Long id);

    @PutMapping(value = APP_ROOT + "/ventes/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Vente par son ID",
            notes = "Cette méthode permet de modifier une Vente par son ID", response = Vente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Vente par son ID a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Vente  avec cet id ID n'a été modifié")

    })
    ResponseEntity<Vente> updateVente(@PathVariable(value = "id") Long id, @RequestBody Vente vente);

    @GetMapping(value = APP_ROOT + "/ventes/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Vente par ID",
            notes = "Cette méthode permet de chercher une Vente par son ID", response = Vente.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Vente a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Vente n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Vente> getVenteById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;


    @GetMapping(value = APP_ROOT + "/ventes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Vente",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Vente", responseContainer = "List<Vente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Vente / une liste vide")
    })
    ResponseEntity<List<Vente>> getAllVentes();

    @GetMapping(value = APP_ROOT + "/ventes/allVenteOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Vente par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Vente par ordre descroissante",
            responseContainer = "List<Vente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Vente / une liste vide")
    })
    ResponseEntity<List<Vente>> getAllVentesOrderDesc();

    @GetMapping(value = APP_ROOT + "/ventes/allVenteOf3LatestMonths", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Vente des trois derniers mois",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Vente des trois derniers mois",
            responseContainer = "List<Vente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Vente / une liste vide")
    })
    ResponseEntity<List<Vente>> getAllVenteOf3LatestMonths();

    @GetMapping(value = APP_ROOT + "/ventes/findTop500OfVenteOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 500 derniers Vente",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 500 derniers Vente",
            responseContainer = "List<Vente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Vente / une liste vide")
    })
    ResponseEntity<List<Vente>> getTop500OVenteOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/ventes/searchVenteWithParticularDayAndMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Vente par jour et mois",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Vente par jour et mois", responseContainer = "List<Vente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ventes par jour et mois / une liste vide")
    })
    ResponseEntity<List<Vente>> getVenteWithParticularDayAndMonth();

    @GetMapping(value = APP_ROOT + "/ventes/searchListVenteByEmpId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Vente par Client",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Vente par client", responseContainer = "List<Vente>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Ventes par Client / une liste vide")
    })
    ResponseEntity<List<Vente>> getListOfVenteByEmployeId(Long empId);

    @GetMapping(value = APP_ROOT + "/ventes/countNumberOfVente")
    @ApiOperation(value = "Compter le nombre de Vente",
            notes = "Cette méthode permet de rechercher et retourner le nombre total Vente"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre total de  Vente / zéro")
    })
    Long getNumberOfVentes();

    @GetMapping(value = APP_ROOT + "/ventes/NumberOfVenteInDay")
    @ApiOperation(value = "Compter le nombre de Vente par jour",
            notes = "Cette méthode permet de rechercher et retourner le nombre total Vente par jour"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre total de  Vente par jour / zéro")
    })
    int getNumberOfVenteInDay();

    @GetMapping(value = APP_ROOT + "/ventes/searchSumsOfVenteInDay")
    @ApiOperation(value = "Compter la somme des Ventes par jours",
            notes = "Cette méthode permet de chercher et renvoyer la montant total des Ventes par jours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant total de Vente par jours / une liste vide")
    })
    BigDecimal getSumsOfVenteInDay();


    @GetMapping(value = APP_ROOT + "/ventes/SumsOfVentesInMonth")
    @ApiOperation(value = "Renvoi le montant total de Vente dans le mois",
            notes = "Cette méthode permet de rechercher et retourner le montant total de Vente dans le mois"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant total total de  Vente par mois / zéro")
    })
    BigDecimal sumTotalOfVentesInMonth();

    @GetMapping(value = APP_ROOT + "/ventes/SumsOfVentesInYear")
    @ApiOperation(value = "Renvoi le montant total de Vente dans une années",
            notes = "Cette méthode permet de rechercher et retourner le montant total de Vente dans une années"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant total total de  Vente dans une années / zéro")
    })
    BigDecimal sumTotalOfVentesInYear();

    @GetMapping(value = APP_ROOT + "/ventes/SumsOfVentes")
    @ApiOperation(value = "Additionner le montant total de Vente",
            notes = "Cette méthode permet de rechercher et retourner le montant total Vente"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant total total de  Vente / zéro")
    })
    BigDecimal getSumsOfVentes();

    @GetMapping(value = APP_ROOT + "/ventes/searchNumberOfVentePeerMonth")
    @ApiOperation(value = "Compter le nombre de Vente par mois",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Vente par mois")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La nombre de Vente par mois / une liste vide")
    })
    List<?> getNumberTotalOfVentePeerMonth();

    @GetMapping(value = APP_ROOT + "/ventes/searchSumVentePeerMonth")
    @ApiOperation(value = "Compter la somme des Ventes par mois",
            notes = "Cette méthode permet de chercher et renvoyer la montant total des Ventes par mois")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant total de Vente par mois / une liste vide")
    })
    List<?> getSumTotalOfVentePeerMonth();

    @GetMapping(value = APP_ROOT + "/ventes/searchSumVentePeerYear")
    @ApiOperation(value = "Compter la somme des Ventes par années",
            notes = "Cette méthode permet de chercher et renvoyer la montant total des Ventes par années")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant total de Vente par années / une liste vide")
    })
    List<?> getSumTotalOfVentePeerYear();

    @DeleteMapping(value = APP_ROOT + "/ventes/delete/{id}")
    @ApiOperation(value = "Supprimer un Vente par son ID",
            notes = "Cette méthode permet de supprimer un Vente par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Vente été supprimé")
    })
    ResponseEntity<?> deleteVente(@PathVariable(value = "id") Long id);


}
