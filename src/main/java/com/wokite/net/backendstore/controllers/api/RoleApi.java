package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Role;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface RoleApi {

    @PostMapping(value = APP_ROOT + "/roles/create")
    @ApiOperation(value = "Enregistrer un Role",
            notes = "Cette méthode permet d'ajouter un Role", response = Role.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Role crée"),
            @ApiResponse(code = 400, message = "Aucun Role crée")

    })
    ResponseEntity<Role> createRole(@RequestBody Role role);

    @PutMapping(value = APP_ROOT + "/roles/update/{idRole}")
    @ApiOperation(value = "Modifier un Role",
            notes = "Cette méthode permet de modifier un Role", response = Role.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Role crée"),
            @ApiResponse(code = 400, message = "Aucun Role crée")

    })
    ResponseEntity<Role> updateRole(@PathVariable(value = "idRole") Long idRole, @RequestBody Role role);

    @GetMapping(value = APP_ROOT + "/roles/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Role par ID",
            notes = "Cette méthode permet de chercher un Role par son ID", response = Role.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Role a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Role n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long idRole) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/roles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Role",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Role", responseContainer = "List<Role>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Role / une liste vide")
    })
    ResponseEntity<List<Role>> getAllRoles();

    @DeleteMapping(value = APP_ROOT + "/roles/delete/{idRole}")
    @ApiOperation(value = "Supprimer un Role par son ID",
            notes = "Cette méthode permet de supprimer un Role par son ID", response = Role.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role a été supprimé")
    })
    ResponseEntity<?> deleteRole(@PathVariable(value = "idRole") Long idRole);

}
