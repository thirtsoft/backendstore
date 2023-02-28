package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.CategoryApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.message.response.ResponseMessage;
import com.wokite.net.backendstore.models.Category;
import com.wokite.net.backendstore.services.CategoryService;
import com.wokite.net.backendstore.services.ExcelService;
import com.wokite.net.backendstore.services.PdfService;
import com.wokite.net.backendstore.utils.ExcelUtils;
import com.wokite.net.backendstore.utils.FileUtils;
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
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;

    private final ExcelService excelService;

//    private final PdfService pdfService;

    private final MessageSource messageSource;

    private final ServletContext context;

    public CategoryController(CategoryService categoryService, ExcelService excelService, MessageSource messageSource, ServletContext context) {
        this.categoryService = categoryService;
        this.excelService = excelService;
        this.messageSource = messageSource;
        this.context = context;
    }

    @Override
    public ResponseEntity<Category> createCategory(Category category) {
        Category categoryResult = categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Category> updateCategory(Long catId, Category category) {
        category.setId(catId);
        Category categoryResult = categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> getCategoryById(Long id) {
        Category category = categoryService.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category that id is" + id + "not found"));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.findAllCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategoriesOrderDesc() {
        List<Category> categoryList = categoryService.findAllCategoriesOrderDesc();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    /*
    @Override
    public void createPdfFileFromCategoriesData(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categoryList = categoryService.findAllCategories();
        boolean isFlag = pdfService.createCategoriesPdf(categoryList, context, request, response);

        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "categories" + ".pdf");
            FileUtils.filedownload(fullPath, response, "categories.pdf");
        }
    }


     */
    @Override
    public ResponseEntity<ResponseMessage> uploadExcelFileToCategoryTable(MultipartFile categoryFile) {
        String message;
        if (ExcelUtils.isExcelFile(categoryFile)) {
            try {
                excelService.storeCategoryFile(categoryFile);
                message = messageSource.getMessage("message.upload.success", null,
                        Locale.getDefault()) + categoryFile.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = messageSource.getMessage("message.upload.fail", null,
                        Locale.getDefault()) + categoryFile.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = messageSource.getMessage("message.upload.notExcelFile", null, Locale.getDefault());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadExcelFileFromCategoriesData() throws IOException {
        List<Category> categoryList = categoryService.findAllCategories();

        ByteArrayInputStream in = ExcelUtils.CategoriesToExcel(categoryList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=categories.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
