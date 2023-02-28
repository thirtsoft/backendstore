package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.models.Category;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.models.SubCategory;
import com.wokite.net.backendstore.services.CategoryService;
import com.wokite.net.backendstore.services.ExcelService;
import com.wokite.net.backendstore.services.ProductService;
import com.wokite.net.backendstore.services.SubCategoryService;
import com.wokite.net.backendstore.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private ExcelUtils utils;

    @Override
    public void store(MultipartFile file) throws Exception {
        try {
            List<Product> productList = utils.parseProduitsExcelFile(file.getInputStream());
            productList.forEach(p -> {
                try {
                    productService.saveProductWithBarcode(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    @Override
    public void storeCategoryFile(MultipartFile file) {
        try {
            List<Category> categoryList = ExcelUtils.parseCategorieExcelFile(file.getInputStream());
            categoryList.forEach(cat -> categoryService.saveCategory(cat));

        } catch (IOException e) {
            throw new RuntimeException("Faill!  -> message = " + e.getMessage());
        }
    }

    @Override
    public void storeSubcategoryFile(MultipartFile file) {
        try {
            List<SubCategory> subCategoryList = utils.parseScategorieExcelFile(file.getInputStream());
            subCategoryList.forEach(scat -> subCategoryService.saveSubCategory(scat));

        } catch (IOException e) {
            throw new RuntimeException("Fail! -> message " + e.getMessage());
        }
    }

    @Override
    public boolean generateExcelFileFromCategoriesData(List<Category> categoryList, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        String filePath = context.getRealPath("/resources/reports");
        File file = new File(filePath);
        boolean exists = new File(filePath).exists();
        if (!exists) {
            new File(filePath).mkdirs();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "categories" + ".xlsx");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("Categories");
            workSheet.setDefaultColumnWidth(30);

            HSSFCellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell code = headerRow.createCell(0);
            code.setCellValue("Code");
            code.setCellStyle(headerCellStyle);

            HSSFCell designation = headerRow.createCell(1);
            designation.setCellValue("Designation");
            designation.setCellStyle(headerCellStyle);

            int i = 1;
            for (Category category : categoryList) {
                HSSFRow bodyRow = workSheet.createRow(i);

                HSSFCellStyle bodyCellStyle = workbook.createCellStyle();

                HSSFCell codeValue = bodyRow.createCell(0);
                codeValue.setCellValue(category.getCode());
                codeValue.setCellStyle(bodyCellStyle);

                HSSFCell designationValue = bodyRow.createCell(1);
                designationValue.setCellValue(category.getDesignation());
                designationValue.setCellStyle(bodyCellStyle);

                i++;

            }

            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean generateExcelFileFromSubCategoriesData(List<SubCategory> subCategoryList, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        String filePath = context.getRealPath("/resources/reports");
        File file = new File(filePath);
        boolean exists = new File(filePath).exists();
        if (!exists) {
            new File(filePath).mkdirs();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "scategories" + ".xlsx");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("Scategories");
            workSheet.setDefaultColumnWidth(30);

            HSSFCellStyle headerCellStyle = workbook.createCellStyle();

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell code = headerRow.createCell(0);
            code.setCellValue("Code");
            code.setCellStyle(headerCellStyle);

            HSSFCell libelle = headerRow.createCell(1);
            libelle.setCellValue("Libelle");
            libelle.setCellStyle(headerCellStyle);


            HSSFCell categorie = headerRow.createCell(2);
            libelle.setCellValue("Categorie");
            libelle.setCellStyle(headerCellStyle);

            int compteur = 1;
            for (SubCategory subCategory : subCategoryList) {
                HSSFRow bodyRow = workSheet.createRow(compteur);

                HSSFCellStyle bodyCellStyle = workbook.createCellStyle();

                HSSFCell codeValue = bodyRow.createCell(0);
                codeValue.setCellValue(subCategory.getCode());
                codeValue.setCellStyle(bodyCellStyle);

                HSSFCell libelleValue = bodyRow.createCell(1);
                libelleValue.setCellValue(subCategory.getLibelle());
                libelleValue.setCellStyle(bodyCellStyle);

                HSSFCell categoryValue = bodyRow.createCell(2);
                categoryValue.setCellValue(subCategory.getCategory().getDesignation());
                categoryValue.setCellStyle(bodyCellStyle);

                compteur++;

            }

            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

            return true;

        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean generateExcelFileBetweenProductsData(List<Product> productList, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        String filePath = context.getRealPath("/resources/reports");
        File file = new File(filePath);
        boolean exists = new File(filePath).exists();
        if (!exists) {
            new File(filePath).mkdirs();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "articles" + ".xlsx");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("Articles");
            workSheet.setDefaultColumnWidth(30);

            HSSFCellStyle headerCellStyle = workbook.createCellStyle();

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell reference = headerRow.createCell(0);
            reference.setCellValue("Reference");
            reference.setCellStyle(headerCellStyle);

            HSSFCell designation = headerRow.createCell(1);
            designation.setCellValue("Designation");
            designation.setCellStyle(headerCellStyle);

            int compteur = 1;
            for (Product product : productList) {
                HSSFRow bodyRow = workSheet.createRow(compteur);

                HSSFCellStyle bodyCellStyle = workbook.createCellStyle();

                HSSFCell referenceValue = bodyRow.createCell(0);
                referenceValue.setCellValue(product.getReference());
                referenceValue.setCellStyle(bodyCellStyle);

                HSSFCell designationValue = bodyRow.createCell(1);
                designationValue.setCellValue(product.getDesignation());
                designationValue.setCellStyle(bodyCellStyle);

                compteur++;

            }

            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

            return true;

        } catch (Exception e) {
            return false;
        }

    }
}
