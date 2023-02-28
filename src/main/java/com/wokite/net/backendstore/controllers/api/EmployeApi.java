package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.Employe;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface EmployeApi {

    @PostMapping(value = APP_ROOT + "/employees/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Employe",
            notes = "Cette méthode permet d'enregistrer un Employe", response = Employe.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employe a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Employe  crée / modifié")

    })
    ResponseEntity<Employe> createEmploye(@RequestBody Employe employe);

    @PutMapping(value = APP_ROOT + "/employees/update/{empId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Employe par son ID",
            notes = "Cette méthode permet de modifier un Employe par son ID", response = Employe.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employe a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Employe modifié")

    })
    ResponseEntity<Employe> updateEmploye(@PathVariable Long empId, @RequestBody Employe employe);

    @GetMapping(value = APP_ROOT + "/employees/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Employe par ID",
            notes = "Cette méthode permet de chercher un Employe par son ID", response = Employe.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employe a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Employe n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Employe> getEmployeById(@PathVariable(value = "id") Long id);

    @GetMapping(value = APP_ROOT + "/employees/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Employe ",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Employe",
            responseContainer = "List<Employe>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des employees/ une liste vide")
    })
    ResponseEntity<List<Employe>>  getAllEmployees();

    @GetMapping(value = APP_ROOT + "/employees/allEmployeOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Employe",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Employe",
            responseContainer = "List<CategorieCharge>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Employe / une liste vide")
    })
    ResponseEntity<List<Employe>> getAllEmployeesOrderDesc();

    @DeleteMapping(value = APP_ROOT + "/employees/delete/{id}")
    @ApiOperation(value = "Supprimer un Employe par son ID",
            notes = "Cette méthode permet de supprimer un Employe par son ID", response = Employe.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employe a été supprimé")
    })
    ResponseEntity<?> deleteEmploye(@PathVariable(value = "id") Long id);


}
