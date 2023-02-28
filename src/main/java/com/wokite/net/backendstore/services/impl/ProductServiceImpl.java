package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneCommande;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.repository.ProductRepository;
import com.wokite.net.backendstore.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        String productinfo = String.valueOf(productRepository.findByReference(product.getBarCode()));
        if (productinfo == product.getBarCode()) {
            throw new IllegalArgumentException("Cet Article existe déjà");
        }
        return productRepository.save(product);
    }

    @Override
    public Product saveProductWithBarcode(Product product) throws Exception {
        Product productResult = new Product();
        if (product.getBarCode() != null) {
            productResult = productRepository.save(product);

        } else {
            //      String productBarCodeResult = ZxingBarcodeHelper.generatedBarCodeWithProduct(product);
            //     productResult.setBarCode(GeneratedNumber.generateCodeCommand());
            productResult.setReference(product.getReference());
            productResult.setDesignation(product.getDesignation());
            productResult.setPrixAchat(product.getPrixAchat());
            productResult.setPrixVente(product.getPrixVente());
            productResult.setPrixVente(product.getPrixVente());
            productResult.setQtestock(product.getQtestock());
            productResult.setStockInitial(product.getStockInitial());
            productResult.setSubCategory(product.getSubCategory());

            productRepository.save(productResult);
        }

        return productResult;
    }

    @Override
    public Product updateProduct(Long prodId, Product product) {
        if (!productRepository.existsById(prodId)) {
            throw new ResourceNotFoundException("Produit that id is" + prodId + "not found");
        }
        Optional<Product> optionalProduct = productRepository.findById(prodId);

        if (!optionalProduct.isPresent()) {
            throw new ResourceNotFoundException("Produit that id is" + prodId + "not found");

        }
        Product productResult = optionalProduct.get();

        productResult.setReference(product.getReference());
        productResult.setBarCode(product.getBarCode());
        productResult.setDesignation(product.getDesignation());
        productResult.setPrixAchat(product.getPrixAchat());
        productResult.setPrixVente(product.getPrixVente());
        productResult.setPrixDetail(product.getPrixDetail());
        productResult.setQtestock(product.getQtestock());
        productResult.setStockInitial(product.getStockInitial());
        productResult.setSubCategory(product.getSubCategory());

        return productRepository.save(productResult);
    }

    @Override
    public Optional<Product> findProductById(Long prodId) {
        if (!productRepository.existsById(prodId)) {
            throw new ResourceNotFoundException("Produit that id is" + prodId + "is not found");
        }
        return productRepository.findById(prodId);
    }

    @Override
    public Optional<Product> findProductByBarcode(String barCode) {
        return productRepository.findByBarCode(barCode);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsOrderDesc() {
        return productRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Product> findListProductByOrderByDesignationAsc() {
        return productRepository.findListProductByOrderByDesignationAsc();
    }


    @Override
    public List<Product> findProductBySubCategoryId(Long subCatId) {
        return productRepository.findProductByScateoryId(subCatId);
    }

    @Override
    public Integer countNumbersOfProductsByStock() {
        return productRepository.countNumbersOfProductsByStock();
    }

    @Override
    public Double capitalDeDepart() {
        List<Product> productList = productRepository.findAll();
        double totalCapital = 0;
        for (Product product : productList) {
            totalCapital += (product.getQtestock() * product.getPrixAchat());
        }
        return totalCapital;
    }

    @Override
    public Integer countNumbersOfProductsWhenQStockEqualStockInit() {
        return productRepository.countNumbersOfProductsWhenQStockEqualStockInit();
    }

    @Override
    public Integer countNumbersOfProductsWhenQStockInfStockInit() {
        return productRepository.countNumbersOfProductsWhenQStockInfStockInit();
    }

    @Override
    public List<?> countNumberOfProductWithStock() {
        return productRepository.countNumberOfProductWithStoc();
    }

    @Override
    public void deleteProduct(Long prodId) {
        if (!productRepository.existsById(prodId)) {
            throw new ResourceNotFoundException("Produit that id is" + prodId + "is not found");
        }
        productRepository.deleteById(prodId);
    }
}
