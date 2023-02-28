package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.message.response.ResponseMessage;
import com.wokite.net.backendstore.models.SubCategory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface SubCategoryApi {

    @PostMapping(value = APP_ROOT + "/subcategories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une SubCategory",
            notes = "Cette méthode permet d'enregistrer et modifier une SubCategory", response = SubCategory.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La SubCategory a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun SubCategory  crée / modifié")
    })
    ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subCategory);

    @PutMapping(value = APP_ROOT + "/subcategories/update/{subCatId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Scategorie par son ID",
            notes = "Cette méthode permet de modifier un Scategorie par son ID", response = SubCategory.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Scategorie avec cet ID a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Scategorie a été modifié avec ID")

    })
    ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long subCatId, @RequestBody SubCategory subCategory);

    @GetMapping(value = APP_ROOT + "/subcategories/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une SubCategory par ID",
            notes = "Cette méthode permet de chercher une SubCategory par son ID", response = SubCategory.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La SubCategory a été trouver"),
            @ApiResponse(code = 404, message = "Aucun SubCategory n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<SubCategory> getSubCategoryById(@PathVariable(value = "id") Long subCatId);

    @GetMapping(value = APP_ROOT + "/subcategories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des SubCategory",
            notes = "Cette méthode permet de chercher et renvoyer la liste des SubCategory", responseContainer = "List<SubCategory>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des SubCategory / une liste vide")
    })
    ResponseEntity<List<SubCategory>> getAllSubCategories();

    @GetMapping(value = APP_ROOT + "/subcategories/allSubCategoriesOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des SubCategory",
            notes = "Cette méthode permet de chercher et renvoyer la liste des SubCategory",
            responseContainer = "List<SubCategory>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des SubCategory / une liste vide")
    })
    ResponseEntity<List<SubCategory>> getAllSubCategoriesOrderDesc();

    @GetMapping(value = APP_ROOT + "/subcategories/searchListSubCategoriesByCategoryId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des SubCategory par categorie",
            notes = "Cette méthode permet de chercher et renvoyer la liste des SubCategory par categorie",
            responseContainer = "List<SubCategory>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des SubCategory par categorie / une liste vide")
    })
    ResponseEntity<List<SubCategory>> getListSubCategoriesByCategoryId(@RequestParam(name = "catId") Long catId);

    /*
    @GetMapping(value = APP_ROOT + "/subcategories/createPdfFileFromSubCategories")
    @ApiOperation(value = "Générer un PDF",
            notes = "Cette méthode permet de générer la liste des scategories sous format pdf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Pdf a été généré")
    })
    void createPdfFileFromSubCategoriesData(HttpServletRequest request, HttpServletResponse response);

     */

    @PostMapping(value = APP_ROOT + "/subcategories/uploadSuCategories")
    @ApiOperation(value = "Importer un fichier excel",
            notes = "Cette méthode permet d'importer le contenu d'un fichier Excel vers la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Fichier a été importé avec succès")
    })
    ResponseEntity<ResponseMessage> uploadExcelFileToSubCategoriesTable(@RequestParam("file") MultipartFile subCategoryFile);

    @GetMapping(value = APP_ROOT + "/subcategories/download/scategories.xlsx")
    @ApiOperation(value = "Exporter un fichier excel",
            notes = "Cette méthode permet d'exporter le contenu d'une BD vers un fichier Excel")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Fichier a été exporté avec succès")
    })
    ResponseEntity<InputStreamResource> createExcelFileFromSubCategoriesData() throws IOException;

    @DeleteMapping(value = APP_ROOT + "/subcategories/delete/{id}")
    @ApiOperation(value = "Supprimer une SubCategory par son ID",
            notes = "Cette méthode permet de supprimer une SubCategory par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La SubCategory a été supprimé")
    })
    ResponseEntity<?> deleteSubCategory(@PathVariable(value = "id") Long subCatId);

}
