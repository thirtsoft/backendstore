package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.message.response.ResponseMessage;
import com.wokite.net.backendstore.models.Category;
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

public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Category",
            notes = "Cette méthode permet d'enregistrer un Category", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Category a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Category  crée / modifié")

    })
    ResponseEntity<Category> createCategory(@RequestBody Category category);

    @PutMapping(value = APP_ROOT + "/categories/update/{catId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Category par son ID",
            notes = "Cette méthode permet de modifier un Category par son ID", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Category modifié")

    })
    ResponseEntity<Category> updateCategory(@PathVariable Long catId, @RequestBody Category category);

    @GetMapping(value = APP_ROOT + "/categories/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Category par ID",
            notes = "Cette méthode permet de chercher un Category par son ID", response = Category.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Category n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long id);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des category ",
            notes = "Cette méthode permet de chercher et renvoyer la liste des category",
            responseContainer = "List<Category>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des category / une liste vide")
    })
    ResponseEntity<List<Category>> getAllCategories();

    @GetMapping(value = APP_ROOT + "/categories/allCategoryOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Categories",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Categories",
            responseContainer = "List<Category>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Categories / une liste vide")
    })
    ResponseEntity<List<Category>> getAllCategoriesOrderDesc();

    /*
    @GetMapping(value = APP_ROOT + "/categories/createCategoriePdf")
    @ApiOperation(value = "Générer un pdf",
            notes = "Cette méthode permet de générer un pdf contenant la liste de Category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pdf a été généré")
    })
    void createPdfFileFromCategoriesData(HttpServletRequest request, HttpServletResponse response);
    */

    @PostMapping(value = APP_ROOT + "/categories/uploadCategories")
    @ApiOperation(value = "Upload file",
            notes = "Cette méthode permet d'importer le contenu d'un file excel et d'envoyer les données vers la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fichier a été importer")
    })
    ResponseEntity<ResponseMessage> uploadExcelFileToCategoryTable(@RequestParam("file") MultipartFile categoryFile);


    @GetMapping(value = APP_ROOT + "/categories/download/categories.xlsx")
    @ApiOperation(value = "Download file",
            notes = "Cette méthode permet d'exporter les données de la BD vers un fichier Excel")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fichier a été exporté")
    })
    ResponseEntity<InputStreamResource> downloadExcelFileFromCategoriesData() throws IOException;


    @DeleteMapping(value = APP_ROOT + "/categories/delete/{id}")
    @ApiOperation(value = "Supprimer un Category par son ID",
            notes = "Cette méthode permet de supprimer un Category par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category a été supprimé")
    })
    ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Long id);


}
