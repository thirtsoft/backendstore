package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.TypeDepense;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface TypeDepenseApi {

    @PostMapping(value = APP_ROOT + "/typedepense/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un CategorieCharge",
            notes = "Cette méthode permet d'enregistrer un CategoryCharge", response = TypeDepense.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CategorieCharge a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun CategorieCharge  crée / modifié")

    })
    ResponseEntity<TypeDepense> createCategoryCharge(@RequestBody TypeDepense typeDepense);

    @PutMapping(value = APP_ROOT + "/typedepense/update/{catId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un CategorieCharge par son ID",
            notes = "Cette méthode permet de modifier un CategorieCharge par son ID", response = TypeDepense.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "CategorieCharge a été modifié"),
            @ApiResponse(code = 400, message = "Aucun CategorieCharge modifié")

    })
    ResponseEntity<TypeDepense> updateCategoryCharge(@PathVariable Long catId, @RequestBody TypeDepense typeDepense);

    @GetMapping(value = APP_ROOT + "/typedepense/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un CategorieCharge par ID",
            notes = "Cette méthode permet de chercher un CategoryCharge par son ID", response = TypeDepense.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "CategorieCharge a été trouver"),
            @ApiResponse(code = 404, message = "Aucun CategorieCharge n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<TypeDepense> getCategoryChargeById(@PathVariable(value = "id") Long id);

    @GetMapping(value = APP_ROOT + "/typedepense/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des category de charge ",
            notes = "Cette méthode permet de chercher et renvoyer la liste des category de charge",
            responseContainer = "List<CategorieCharge>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des category de charge / une liste vide")
    })
    ResponseEntity<List<TypeDepense>> getAllCategoriesCharges();

    @GetMapping(value = APP_ROOT + "/typedepense/allCategoriesChargeOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des CategorieCharge",
            notes = "Cette méthode permet de chercher et renvoyer la liste des CategorieCharge",
            responseContainer = "List<CategorieCharge>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des CategorieCharge / une liste vide")
    })
    ResponseEntity<List<TypeDepense>> getAllCategoriesChargeOrderDesc();

    @DeleteMapping(value = APP_ROOT + "/typedepense/delete/{id}")
    @ApiOperation(value = "Supprimer un CategorieCharge par son ID",
            notes = "Cette méthode permet de supprimer un CategorieCharge par son ID", response = TypeDepense.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "CategorieCharge a été supprimé")
    })
    ResponseEntity<?> deleteCategoryCharge(@PathVariable(value = "id") Long id);


}
