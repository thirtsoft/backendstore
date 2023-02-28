package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.Contrat;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface ContratApi {

    @PostMapping(value = APP_ROOT + "/contrats/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Contrat",
            notes = "Cette méthode permet d'enregistrer un Contrat", response = Contrat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Contrat a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Contrat  crée / modifié")

    })
    ResponseEntity<Contrat> createContrat(@RequestBody Contrat contrat);

    @PostMapping(value = APP_ROOT + "/contrats/createContrats")
    @ApiOperation(value = "Enregistrer un Contrat avec le document",
            notes = "Cette méthode permet d'enregistrer un Contrat avec le document", response = Contrat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Contrat a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Contrat crée / modifié")

    })
    ResponseEntity<?> createContrat(@RequestPart(name = "contrat") String cont,
                                    @RequestParam(name = "file") MultipartFile file) throws IOException;

    @PostMapping(value = APP_ROOT + "/contrats/addContratInPath")
    @ApiOperation(value = "Enregistrer un Contrat avec le document",
            notes = "Cette méthode permet d'enregistrer un Contrat avec le document", response = Contrat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Contrat a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Contrat crée / modifié")

    })
    ResponseEntity<?> addContratInPath(@RequestPart(name = "contrat") String cont,
                                       @RequestParam(name = "file") MultipartFile file) throws IOException;

    @GetMapping(value = APP_ROOT + "/contrats/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Contrat par ID",
            notes = "Cette méthode permet de chercher un Contrat par son ID", response = Contrat.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contrat a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Contrat n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Contrat> getContratById(@PathVariable(value = "id") Long id);


    @PutMapping(value = APP_ROOT + "/contrats/update/{catId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Contrat par son ID",
            notes = "Cette méthode permet de modifier un Contrat par son ID", response = Contrat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "CategorieCharge a été modifié"),
            @ApiResponse(code = 400, message = "Aucun CategorieCharge modifié")

    })
    ResponseEntity<Contrat> updateContrat(@PathVariable Long catId, @RequestBody Contrat contrat);

    @GetMapping(value = APP_ROOT + "/contrats/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des contrats ",
            notes = "Cette méthode permet de chercher et renvoyer la liste des contrats",
            responseContainer = "List<Contrat>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Contrat/ une liste vide")
    })
    ResponseEntity<List<Contrat>> getAllContrats();

    @GetMapping(value = APP_ROOT + "/contrats/allContratOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Contrat",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Contrat",
            responseContainer = "List<Contrat>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Contrat / une liste vide")
    })
    ResponseEntity<List<Contrat>> getAllContratsOrderDesc();


    @GetMapping(value = APP_ROOT + "/contrats/searchListContratsByClientId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Contrat d'un client donnée",
            notes = "Cette méthode permet de chercher et renvoyer la liste des contrats d'un client donnée",
            responseContainer = "List<Contrat>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Contrat / une liste vide")
    })
    ResponseEntity<List<Contrat>> getContratsByClientId(@RequestParam("clientId") Long clientId);

    @PostMapping(value = APP_ROOT + "/contrats/uploadFilePdf/{id}")
    @ApiOperation(value = "importer un Contrat",
            notes = "Cette méthode permet d'importer un contrat pdf depuis le disque vers la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Contrat a été importé")
    })
    void uploadContratFile(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @RequestMapping(value = APP_ROOT + "/contrats/downloadContratFile/{fileName:.+}")
    @ApiOperation(value = "Télécharger un Contrat par son ID",
            notes = "Cette méthode permet de télécharger un Contrat par son ID", response = Contrat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Contrat a été télécharger")
    })
    void downloadContratFile(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("fileName") String fileName) throws IOException;

    @DeleteMapping(value = APP_ROOT + "/contrats/delete/{id}")
    @ApiOperation(value = "Supprimer un Contrat par son ID",
            notes = "Cette méthode permet de supprimer un Contrat par son ID", response = Contrat.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contrat a été supprimé")
    })
    ResponseEntity<?> deleteContrat(@PathVariable(value = "id") Long id);


}
