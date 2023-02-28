package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Product;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);

    Product saveProductWithBarcode(Product product) throws Exception;

    Product updateProduct(Long prodId, Product product);

    Optional<Product> findProductById(Long prodId);

    Optional<Product> findProductByBarcode(String barCode);

    List<Product> findAllProducts();

    List<Product> findAllProductsOrderDesc();

    List<Product> findListProductByOrderByDesignationAsc();

    List<Product> findProductBySubCategoryId(Long subCatId);

    Integer countNumbersOfProductsByStock();

    Double capitalDeDepart();

    Integer countNumbersOfProductsWhenQStockEqualStockInit();

    Integer countNumbersOfProductsWhenQStockInfStockInit();

    List<?> countNumberOfProductWithStock();

    void deleteProduct(Long prodId);

}
