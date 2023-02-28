package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.Commande;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface CommandeApi {

    @PostMapping(value = APP_ROOT + "/commandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Commande",
            notes = "Cette méthode permet d'enregistrer un Commande", response = Commande.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Commande a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Commande  crée / modifié")

    })
    ResponseEntity<Commande> createCommande(@RequestBody Commande commande);

    @PostMapping(value = APP_ROOT + "/commandes/enregistrerCommade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ajouter une commande",
            notes = "Cette méthode permet d'ajouter une commande", response = Commande.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande a été ajouté"),
            @ApiResponse(code = 400, message = "Aucun commande a été ajouté")

    })
    ResponseEntity<Commande> enregistrerCommande(@RequestBody Commande commande, @RequestParam Long id);

    @PostMapping(value = APP_ROOT + "/commandes/createCommade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ajouter une commande",
            notes = "Cette méthode permet d'ajouter une commande", response = Commande.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande a été ajouté"),
            @ApiResponse(code = 400, message = "Aucun commande a été ajouté")

    })
    ResponseEntity<Commande> createCommande(@RequestBody Commande commande, @RequestParam Long id);

    @PostMapping(value = APP_ROOT + "/commandes/commandesClientes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande",
            notes = "Cette méthode permet d'enregistrer et modifié une commande", response = Commande.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La commande a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun commande a été crée / modifié")

    })
    ResponseEntity<Commande> createCommandeClient(@RequestBody Commande commandeClient, @RequestParam Long id);

    @PutMapping(value = APP_ROOT + "/commandes/update/{catId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Commande par son ID",
            notes = "Cette méthode permet de modifier un Commande par son ID", response = Commande.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "CategorieCharge a été modifié"),
            @ApiResponse(code = 400, message = "Aucun CategorieCharge modifié")

    })
    ResponseEntity<Commande> updateCommande(@PathVariable Long catId, @RequestBody Commande commande);

    @GetMapping(value = APP_ROOT + "/commandes/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Commande par ID",
            notes = "Cette méthode permet de chercher un Commande par son ID", response = Commande.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commande a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Commande n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Commande> getCommandeById(@PathVariable(value = "id") Long id);

    @GetMapping(value = APP_ROOT + "/commandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes ",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes",
            responseContainer = "List<Commande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commande/ une liste vide")
    })
    ResponseEntity<List<Commande>>  getAllCommandes();

    @GetMapping(value = APP_ROOT + "/commandes/allCommandeClientOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commande",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commande",
            responseContainer = "List<Commande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commande / une liste vide")
    })
    ResponseEntity<List<Commande>> getAllCommandesOrderDesc();

    @GetMapping(value = APP_ROOT + "/commandes/allCommandesOf3LatestMonths", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commande des trois derniers mois",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commande des trois derniers mois",
            responseContainer = "List<Commande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commande / une liste vide")
    })
    ResponseEntity<List<Commande>> getAllCommandesOf3LatestMonths();

    @GetMapping(value = APP_ROOT + "/commandes/findTop500OCommandesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 500 derniers Commande",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 500 derniers Commande",
            responseContainer = "List<Commande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commande / une liste vide")
    })
    ResponseEntity<List<Commande>> getTop500OCommandesClientOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/commandes/searchListCommandesByClientId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commande d'un client donnée",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes d'un client donnée",
            responseContainer = "List<Commande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commande / une liste vide")
    })
    ResponseEntity<List<Commande>> getAllCommandesByClientId(@RequestParam("clientId") Long clientId);

    @GetMapping(value = APP_ROOT + "/commandes/searchCommandewithdate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commande entre 2 dates",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes entre 2 dates",
            responseContainer = "List<Commande>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commande / une liste vide")
    })
    ResponseEntity<List<Commande>>getAllCommandesWithDatet(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateCommande);


    @GetMapping(value = APP_ROOT + "/commandes/countNumbersOfCommandes")
    @ApiOperation(value = "Compter le nombre de Commande",
            notes = "Cette méthode permet de compter le nombre total de CommandeClient")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Commande / une liste vide")
    })
    Long getNumbersOfCommandes();

    @GetMapping(value = APP_ROOT + "/commandes/SumsOfCommandesInMonth")
    @ApiOperation(value = "Additionner les commandes par mois",
            notes = "Cette méthode permet d'additionner le total des commandes du mois")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La somme des commandes par mois / une liste vide")
    })
    BigDecimal sumTotalOfCommandesInMonth();

    @GetMapping(value = APP_ROOT + "/commandes/SumsOfCommandesInYear")
    @ApiOperation(value = "Additionner les commandes dans une annnées",
            notes = "Cette méthode permet d'additionner le total des commandes dans une annnées")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La somme des commandes par annnées / une liste vide")
    })
    BigDecimal sumTotalOfCommandesInYear();

    @GetMapping(value = APP_ROOT + "/commandes/numberOfCommandesPeerMonth")
    @ApiOperation(value = "Compter le nombre de commande par mois",
            notes = "Cette méthode permet de Compter le nombre de commande par mois")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La somme des commandes par annnées / une liste vide")
    })
    List<?> getNumberTotalOfCommandePeerMonth();

    @GetMapping(value = APP_ROOT + "/commandes/sumOfCommandesPeerMonth")
    @ApiOperation(value = "Compter la somme total de commande par mois",
            notes = "Cette méthode permet de Compter le nombre de commande par mois")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La somme des commandes par annnées / une liste vide")
    })
    List<?> getSumTotalOfCommandePeerMonth();




    @DeleteMapping(value = APP_ROOT + "/commandes/delete/{id}")
    @ApiOperation(value = "Supprimer un Commande par son ID",
            notes = "Cette méthode permet de supprimer un Commande par son ID", response = Commande.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commande a été supprimé")
    })
    ResponseEntity<?> deleteCommande(@PathVariable(value = "id") Long id);




    }
