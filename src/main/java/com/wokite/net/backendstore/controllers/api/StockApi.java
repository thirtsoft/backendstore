package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.Stock;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface StockApi {

    @PostMapping(value = APP_ROOT + "/stocks/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Stock",
            notes = "Cette méthode permet d'enregistrer un Stock", response = Stock.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Stock a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Stock  crée / modifié")

    })
    ResponseEntity<Stock> createStock(@RequestBody Stock stock);

    @PutMapping(value = APP_ROOT + "/stocks/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Stock par son ID",
            notes = "Cette méthode permet de modifier un Stock par son ID", response = Stock.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Stock avec cet ID a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Stock  modifié avec cet ID")

    })
    ResponseEntity<Stock> updateStock(@PathVariable Long stockId, @RequestBody Stock stock);

    @GetMapping(value = APP_ROOT + "/stocks/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Stock par id",
            notes = "Cette méthode permet de chercher une Stock par id", response = Stock.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Stock avec l'id a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Stock n'existe avec cet id pas dans la BD")

    })
    ResponseEntity<Stock> getStockById(@PathVariable(value = "id") Long id);


    @GetMapping(value = APP_ROOT + "/stocks/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Stock",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Stock", responseContainer = "List<Stock>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Stock / une liste vide")
    })
    ResponseEntity<List<Stock>> getAllStocks();

    @GetMapping(value = APP_ROOT + "/stocks/searchListStocksByProductId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Stock par produit",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Stock par produit", responseContainer = "List<Stock>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Charge par produit / une liste vide")
    })
    ResponseEntity<List<Stock>> getListStocksByProductId(@RequestParam("id") Long prodId);

    @DeleteMapping(value = APP_ROOT + "/stocks/delete/{id}")
    @ApiOperation(value = "Supprimer un Stock par son ID",
            notes = "Cette méthode permet de supprimer un Stock par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Stock a été supprimé")
    })
    ResponseEntity<?> deleteStock(@PathVariable(value = "id") Long stockId);


}
