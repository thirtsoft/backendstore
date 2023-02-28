package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Category;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.models.SubCategory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ExcelService {

    void store(MultipartFile file) throws Exception;

    void storeCategoryFile(MultipartFile file);

    void storeSubcategoryFile(MultipartFile file);

    boolean generateExcelFileFromCategoriesData(List<Category> categoryList, ServletContext context, HttpServletRequest request, HttpServletResponse response);

    boolean generateExcelFileFromSubCategoriesData(List<SubCategory> subCategoryList, ServletContext context, HttpServletRequest request, HttpServletResponse response);

    boolean generateExcelFileBetweenProductsData(List<Product> productList, ServletContext context, HttpServletRequest request, HttpServletResponse response);



}
