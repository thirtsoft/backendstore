package com.wokite.net.backendstore.utils;

import com.wokite.net.backendstore.models.Category;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.models.SubCategory;
import com.wokite.net.backendstore.services.CategoryService;
import com.wokite.net.backendstore.services.SubCategoryService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelUtils {

    public static String EXCELTYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private CategoryService categoryService;


    public static boolean isExcelFile(MultipartFile file) {
        return EXCELTYPE.equals(file.getContentType());
    }

    public static List<Category> parseCategorieExcelFile(InputStream inputStream) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheet("Categories");
            Iterator<Row> rows = sheet.iterator();
            List<Category> categories = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                //skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Category categorie = new Category();
                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 0:
                            categorie.setCode(currentCell.getStringCellValue());
                            break;
                        case 1:
                            categorie.setDesignation(currentCell.getStringCellValue());
                            break;
                    }
                    cellIndex++;
                }
                categories.add(categorie);
            }
            return categories;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public static ByteArrayInputStream CategoriesToExcel(List<Category> categoryList) throws IOException {

        String[] COLUMNs = {"Code", "Designation"};

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Categories");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }


            int rowIdx = 1;
            for (Category category : categoryList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(category.getCode());
                row.createCell(1).setCellValue(category.getDesignation());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static ByteArrayInputStream ScategoriesToExcel(List<SubCategory> subCategoryList) throws IOException {

        String[] COLUMNs = {"Code", "Libelle", "Categorie"};

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Scategories");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (SubCategory subCategory : subCategoryList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(subCategory.getCode());
                row.createCell(1).setCellValue(subCategory.getLibelle());
                row.createCell(2).setCellValue(subCategory.getCategory().getDesignation());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static ByteArrayInputStream produitsToExcel(List<Product> productList) throws IOException {

        String[] COLUMNs = {"Barcode", "Reference", "Designation", "Prix_Achat", "Prix_Vente", "Prix_Detail", "Stock", "StockInitial", "Scategorie"};

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Produits");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (Product product : productList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(product.getBarCode());
                row.createCell(1).setCellValue(product.getReference());
                row.createCell(2).setCellValue(product.getDesignation());
                row.createCell(3).setCellValue(product.getPrixAchat());
                row.createCell(4).setCellValue(product.getPrixVente());
                row.createCell(5).setCellValue(product.getPrixDetail());
                row.createCell(6).setCellValue(product.getQtestock());
                row.createCell(7).setCellValue(product.getStockInitial());

                row.createCell(8).setCellValue(product.getSubCategory().getLibelle());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public List<SubCategory> parseScategorieExcelFile(InputStream inputStream) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheet("Scategories");
            Iterator<Row> rows = sheet.iterator();
            List<SubCategory> subCategoryList = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                //skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                SubCategory subCategory = new SubCategory();
                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 0:
                            subCategory.setCode(currentCell.getStringCellValue());
                            break;
                        case 1:
                            subCategory.setLibelle(currentCell.getStringCellValue());
                            break;
                        case 2:
                            Category category = categoryService.findByDesignation(currentCell.getStringCellValue());
                            subCategory.setCategory(category);
                            break;
                    }
                    cellIndex++;
                }
                subCategoryList.add(subCategory);
            }
            return subCategoryList;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public List<Product> parseProduitsExcelFile(InputStream inputStream) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheet("Produits");
            Iterator<Row> rows = sheet.iterator();
            List<Product> productList = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                //skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Product product = new Product();
                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 0:
                            product.setBarCode(String.format("%.0f",currentCell.getNumericCellValue()));
                            break;
                        case 1:
                            product.setReference(currentCell.getStringCellValue());
                            break;
                        case 2:
                            product.setDesignation(currentCell.getStringCellValue());
                            break;
                        case 3:
                            product.setPrixAchat(currentCell.getNumericCellValue());
                            break;
                        case 4:
                            product.setPrixVente(currentCell.getNumericCellValue());
                            break;
                        case 5:
                            product.setPrixDetail(currentCell.getNumericCellValue());
                            break;
                        case 6:
                            product.setQtestock((int) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            product.setStockInitial((int) currentCell.getNumericCellValue());
                            break;
                        case 8:
                            SubCategory subCategory = subCategoryService.findByLibelle(currentCell.getStringCellValue());
                            product.setSubCategory(subCategory);
                            break;
                    }
                    cellIndex++;
                }
                productList.add(product);
            }
            return productList;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public List<Product> parseExcelFile(InputStream inputStream) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheet("Produits");
            Iterator<Row> rows = sheet.iterator();
            List<Product> productList = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                //skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Product product = new Product();
                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 0:
                            product.setBarCode(currentCell.getStringCellValue());
                            break;
                        case 1:
                            product.setReference(currentCell.getStringCellValue());
                            break;
                        case 2:
                            product.setDesignation(currentCell.getStringCellValue());
                            break;
                        case 3:
                            product.setPrixAchat(currentCell.getNumericCellValue());
                            break;
                        case 4:
                            product.setPrixVente(currentCell.getNumericCellValue());
                            break;
                        case 5:
                            product.setPrixDetail(currentCell.getNumericCellValue());
                            break;
                        case 6:
                            product.setQtestock((int) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            product.setStockInitial((int) currentCell.getNumericCellValue());
                            break;
                        case 8:
                            SubCategory subCategory = subCategoryService.findByLibelle(currentCell.getStringCellValue());
                            product.setSubCategory(subCategory);
                            break;
                    }
                    cellIndex++;
                }
                productList.add(product);
            }
            return productList;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

}
