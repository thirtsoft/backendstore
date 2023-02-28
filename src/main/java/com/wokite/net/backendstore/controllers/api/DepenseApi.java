package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.Depense;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface DepenseApi {

    @PostMapping(value = APP_ROOT + "/charges/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Charge",
            notes = "Cette méthode permet d'enregistrer un Charge", response = Depense.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Charge a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Charge  crée / modifié")

    })
    ResponseEntity<Depense> createCharge(@RequestBody Depense depense);

    @PutMapping(value = APP_ROOT + "/charges/update/{catId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Charge par son ID",
            notes = "Cette méthode permet de modifier un Charge par son ID", response = Depense.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Charge a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Charge modifié")

    })
    ResponseEntity<Depense> updateCharge(@PathVariable Long catId, @RequestBody Depense depense);

    @GetMapping(value = APP_ROOT + "/charges/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Charge par ID",
            notes = "Cette méthode permet de chercher un Charge par son ID", response = Depense.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Charge a été trouver"),
            @ApiResponse(code = 404, message = "Aucun CategorieCharge n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Depense> getChargeById(@PathVariable(value = "id") Long id);

    @GetMapping(value = APP_ROOT + "/charges/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des category de charge ",
            notes = "Cette méthode permet de chercher et renvoyer la liste des category de charge",
            responseContainer = "List<Charge>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des category de charge / une liste vide")
    })
    ResponseEntity<List<Depense>>  getAllCharges();

    @GetMapping(value = APP_ROOT + "/charges/allChargeOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Charge",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Charge",
            responseContainer = "List<Charge>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Charge / une liste vide")
    })
    ResponseEntity<List<Depense>> getAllChargesOrderDesc();

    @GetMapping(value = APP_ROOT + "/charges/sumTotalChargeInYear")
    @ApiOperation(value = "Renvoi le montant total des Charges dans années",
            notes = "Cette méthode permet de chercher et le montant total des Charges dans années")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant total des Charges par années")
    })
    BigDecimal sumTotalOfChargeInYear();

    @GetMapping(value = APP_ROOT + "/charges/sumMontantTotalChargePeerMonth")
    @ApiOperation(value = "Renvoi le montant total des Charges par month",
            notes = "Cette méthode permet de chercher et le montant total des Charges par month",
            responseContainer = "List<Charge>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant total des Charges par month / une liste vide")
    })
    List<?> sumTotalOfChargePeerMonth();

    @DeleteMapping(value = APP_ROOT + "/charges/delete/{id}")
    @ApiOperation(value = "Supprimer un Charge par son ID",
            notes = "Cette méthode permet de supprimer un Charge par son ID", response = Depense.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Charge a été supprimé")
    })
    ResponseEntity<?> deleteCharge(@PathVariable(value = "id") Long id);


}
