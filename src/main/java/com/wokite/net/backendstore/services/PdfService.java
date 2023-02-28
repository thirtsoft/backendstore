package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Category;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.models.SubCategory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PdfService {

    boolean createCategoriesPdf(List<Category> categoryList, ServletContext context, HttpServletRequest request, HttpServletResponse response);

    boolean createSubCategoriesPdf(List<SubCategory> subCategoryList, ServletContext context, HttpServletRequest request, HttpServletResponse response);

    boolean createPdfProducts(List<Product> productList, ServletContext context, HttpServletRequest request, HttpServletResponse response);


}
