package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.message.response.ResponseMessage;
import com.wokite.net.backendstore.models.Product;
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

public interface ProductApi {

    @PostMapping(value = APP_ROOT + "/products/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Product",
            notes = "Cette méthode permet d'enregistrer et modifier un Product", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Product a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Product  crée / modifié")

    })
    ResponseEntity<Product> createProduct(@RequestBody Product product);

    @PostMapping(value = APP_ROOT + "/products/createProductWithBarcode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Product avec code-barre",
            notes = "Cette méthode permet d'enregistrer et modifier un Product", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Product a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Product  crée / modifié")

    })
    ResponseEntity<Product> createProductWithBarCode(@RequestBody Product Product) throws Exception;

    @PutMapping(value = APP_ROOT + "/products/update/{prodId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Product par son ID",
            notes = "Cette méthode permet de modifier un Product par son ID", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Product a été modifié avec succès"),
            @ApiResponse(code = 400, message = "Aucun Product n'a été modifié")

    })
    ResponseEntity<Product> updateProduct(@PathVariable(value = "prodId") Long prodId, @RequestBody Product product);

    @GetMapping(value = APP_ROOT + "/products/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Product par ID",
            notes = "Cette méthode permet de chercher une Product par son ID", response = Product.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Product a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Product n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long prodId);

    @GetMapping(value = APP_ROOT + "/products/searchProductByBarCode/{barcode}")
    @ApiOperation(value = "chercher un Product par son code-barre",
            notes = "Cette méthode permet de chercher et renvoyer un Product par son code-barre")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Product a été trouvé")
    })
    ResponseEntity<Product> getProductByBarCode(@PathVariable(value = "barcode") String barcode);

    @GetMapping(value = APP_ROOT + "/products/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Product",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Product", responseContainer = "List<Product>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Product / une liste vide")
    })
    ResponseEntity<List<Product>> getAllProducts();

    @GetMapping(value = APP_ROOT + "/products/allProductOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Product",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Product",
            responseContainer = "List<Product>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Product / une liste vide")
    })
    ResponseEntity<List<Product>> getAllProductOrderDesc();

    @GetMapping(value = APP_ROOT + "/products/searchListProductsBySubCategoryId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Product par scategorie",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Product par scategorie", responseContainer = "List<Product>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Product par scategorie / une liste vide")
    })
    ResponseEntity<List<Product>> getProductsBySubCategoriesId(@RequestParam("scaId") Long scatId);

    @GetMapping(value = APP_ROOT + "/products/countProductsByStock")
    Integer countNumbersOfProductsByStock();

    @GetMapping(value = APP_ROOT + "/products/countProductsWhenQStockEqualStockInit")
    @ApiOperation(value = "Compter le nombre de Product par quantity",
            notes = "Cette méthode permet de compter le nombre de Product dont la quantity de stock est égale à la quantity initial  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Product par quantity / zéro")
    })
    Integer countNumbersOfProductsWhenQStockEqualStockInit();

    @GetMapping(value = APP_ROOT + "/products/countProductsWhenQStockInfStockInit")
    @ApiOperation(value = "Compter le nombre de Product négatif",
            notes = "Cette méthode permet de compter le nombre de Product dont la quantity de stock est négatif")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Product négatif / zéro")
    })
    Integer countNumbersOfProductsWhenQStockInfStockInit();

    @GetMapping(value = APP_ROOT + "/products/capitalDeDepart")
    @ApiOperation(value = "Donne la somme de départ du magasin",
            notes = "Cette méthode permet d'indiquer la somme total de départ du magasin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La capital de départ est / zéro")
    })
    Double getCapitalDeDepart();

    @GetMapping(value = APP_ROOT + "/products/searchCountProductsByStock")
    @ApiOperation(value = "Compter le nombre de Product",
            notes = "Cette méthode permet de compter le nombre de Product dont la quantity de stock est positif")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Product / zéro")
    })
    List<?> countNumberOfProductWithStock();

    /*
    @GetMapping(value = APP_ROOT + "/products/createPdf")
    @ApiOperation(value = "Générer un PDf",
            notes = "Cette méthode permet de générer la liste des products sous format Pdf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Pdf a été supprimé")
    })
    void createPdfFileFromProductsData(HttpServletRequest request, HttpServletResponse response);

    */

    @PostMapping(value = APP_ROOT + "/products/upload")
    @ApiOperation(value = "Importer un Excel",
            notes = "Cette méthode permet d'importer la liste de products contenu dans un fichier Excel vers la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Fichier Excel a été importé")
    })
    ResponseEntity<ResponseMessage> uploadExcelFileToProductTable(@RequestParam("file") MultipartFile productFile);

    @GetMapping(value = APP_ROOT + "/products/download/articles.xlsx")
    @ApiOperation(value = "Exporter la liste de Product",
            notes = "Cette méthode permet d'exporter la liste de products de la BD vers un fichier Excel")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Fichier Excel a été télécharger")
    })
    ResponseEntity<InputStreamResource> createExcelFileFromProductsData() throws IOException;

    @DeleteMapping(value = APP_ROOT + "/products/delete/{id}")
    @ApiOperation(value = "Supprimer un Product par son ID",
            notes = "Cette méthode permet de supprimer un Product par son ID", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Product a été supprimé")
    })
    ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long prodId);


}
