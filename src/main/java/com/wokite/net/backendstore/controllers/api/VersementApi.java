package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.Versement;
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

public interface VersementApi {


    @PostMapping(value = APP_ROOT + "/versements/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Versement",
            notes = "Cette méthode permet d'enregistrer un Versement", response = Versement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Versement a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Versement n'a pas été  crée / modifié")

    })
    ResponseEntity<Versement> saveVersement(@RequestBody Versement versement);

    @PostMapping(value = APP_ROOT + "/versements/createVersement")
    ResponseEntity<?> createVersement(@RequestPart(name = "versement") String vers,
                                      @RequestParam(name = "file") MultipartFile file) throws IOException;

    @PostMapping(value = APP_ROOT + "/versements/createVersementInPath")
    @ApiOperation(value = "Enregistrer un Versement avec son fichier",
            notes = "Cette méthode permet d'enregistrer un Versement", response = Versement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Versement a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Versement n'a pas été  crée / modifié")

    })
    ResponseEntity<?> createVersementInPath(@RequestPart(name = "versement") String vers,
                                            @RequestParam(name = "file") MultipartFile file) throws IOException;

    @PutMapping(value = APP_ROOT + "/versements/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Versement",
            notes = "Cette méthode permet de modifier un Versement", response = Versement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Versement a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Versement n'a été modifié")

    })
    ResponseEntity<Versement> updateVersement(@PathVariable Long id, @RequestBody Versement versement);

    @GetMapping(value = APP_ROOT + "/versements/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Versement par ID",
            notes = "Cette méthode permet de chercher un Versement par son ID", response = Versement.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Versement a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Versement n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Versement> getVersementById(@PathVariable(value = "id") Long id);

    @GetMapping(value = APP_ROOT + "/versements/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Versement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Versement", responseContainer = "List<Versement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Charge / une liste vide")
    })
    ResponseEntity<List<Versement>> getAllVersements();

    @GetMapping(value = APP_ROOT + "/versements/allVersementderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Versement",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Vente",
            responseContainer = "List<Versement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Versement / une liste vide")
    })
    ResponseEntity<List<Versement>> getAllVersementOrderDesc();

    @GetMapping(value = APP_ROOT + "/versements/searchListVersementsByEmployeId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Versement par Employe",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Versement par employe", responseContainer = "List<Versement>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Versement par employe / une liste vide")
    })
    ResponseEntity<List<Versement>> getAllVersementsByEmployeId(Long empId);

    @PostMapping(value = APP_ROOT + "/versements/uploadPdfFile/{id}")
    @ApiOperation(value = "Importer un PDF",
            notes = "Cette méthode permet d'importer un fichier PDf d'un dossier externe vers la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'importation du fichier  s'est passé avec succès")
    })
    void uploadVersementFile(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @PostMapping(value = APP_ROOT + "/versements/uploadPdfFileInPath/{id}")
    @ApiOperation(value = "Importer un PDF",
            notes = "Cette méthode permet d'importer un fichier PDf d'un dossier externe vers la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'importation du fichier  s'est passé avec succès")
    })
    void uploadVersementFileInPath(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @RequestMapping(value = APP_ROOT + "/versements/downloadVersementFile/{fileName:.+}")
    @ApiOperation(value = "Télécharger un PDF",
            notes = "Cette méthode permet de télécharger un le reçu d'un  Versement")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Versement a été téléchargé")
    })
    void downloadVersementFile(HttpServletRequest request, HttpServletResponse response,
                               @PathVariable("fileName") String fileName) throws IOException;

    @DeleteMapping(value = APP_ROOT + "/versements/delete/{id}")
    @ApiOperation(value = "Supprimer un Versement par son ID",
            notes = "Cette méthode permet de supprimer un Versement par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Versement a été supprimé")
    })
    ResponseEntity<?> deleteVersement(@PathVariable(value = "id") Long id);


}
