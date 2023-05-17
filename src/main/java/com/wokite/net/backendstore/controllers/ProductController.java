package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.ProductApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.message.response.ResponseMessage;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.services.ExcelService;
import com.wokite.net.backendstore.services.PdfService;
import com.wokite.net.backendstore.services.ProductService;
import com.wokite.net.backendstore.utils.ExcelUtils;
import com.wokite.net.backendstore.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@CrossOrigin
@RestController
public class ProductController implements ProductApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private ExcelService excelService;

//    private PdfService pdfService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ExcelUtils utils;

    @Autowired
    private ServletContext context;

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        Product productResult = productService.saveProduct(product);
        return new ResponseEntity<>(productResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Product> createProductWithBarCode(Product product) throws Exception {
        Product productResult = productService.saveProductWithBarcode(product);
        return new ResponseEntity<>(productResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Long prodId, Product product) {
        product.setId(prodId);
        Product productResult = productService.saveProduct(product);
        return new ResponseEntity<>(productResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProductById(Long prodId) {
        Product productResult = productService.findProductById(prodId)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + prodId + "not found"));
        return new ResponseEntity<>(productResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProductByBarCode(String barcode) {
        Product productResult = productService.findProductByBarcode(barcode)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + barcode + "not found"));
        return new ResponseEntity<>(productResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.findAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProductOrderDesc() {
        List<Product> productList = productService.findAllProductsOrderDesc();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProductOrderAsc() {
        List<Product> productList = productService.findListProductByOrderByDesignationAsc();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getProductsBySubCategoriesId(Long scatId) {
        List<Product> productList = productService.findProductBySubCategoryId(scatId);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @Override
    public Integer countNumbersOfProductsByStock() {
        return productService.countNumbersOfProductsByStock();
    }

    @Override
    public Integer countNumbersOfProductsWhenQStockEqualStockInit() {
        return productService.countNumbersOfProductsWhenQStockInfStockInit();
    }

    @Override
    public Integer countNumbersOfProductsWhenQStockInfStockInit() {
        return productService.countNumbersOfProductsWhenQStockInfStockInit();
    }

    @Override
    public Double getCapitalDeDepart() {
        return productService.capitalDeDepart();
    }

    @Override
    public List<?> countNumberOfProductWithStock() {
        return productService.countNumberOfProductWithStock();
    }

    /*
    @Override
    public void createPdfFileFromProductsData(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = productService.findAllProducts();
        boolean isFlag = pdfService.createPdfProducts(productList, context, request, response);
        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "articles" + ".pdf");
            FileUtils.filedownload(fullPath, response, "articles.pdf");
        }
    }


     */
    @Override
    public ResponseEntity<ResponseMessage> uploadExcelFileToProductTable(MultipartFile productFile) {
        String message;
        if (ExcelUtils.isExcelFile(productFile)) {
            try {
                excelService.store(productFile);
                message = messageSource.getMessage("message.upload.success", null, Locale.getDefault()) + productFile.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = messageSource.getMessage("message.upload.fail", null, Locale.getDefault()) + productFile.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = messageSource.getMessage("message.upload.notExcelFile", null, Locale.getDefault());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @Override
    public ResponseEntity<InputStreamResource> createExcelFileFromProductsData() throws IOException {

        List<Product> productList = productService.findAllProducts();

        ByteArrayInputStream in = ExcelUtils.produitsToExcel(productList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=articles.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long prodId) {
        productService.deleteProduct(prodId);
        return ResponseEntity.ok()
                .build();
    }
}
