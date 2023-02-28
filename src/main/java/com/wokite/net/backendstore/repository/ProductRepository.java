package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByReference(String reference);

    @Query("select p from Product p where p.designation like :des")
    Product findByDesignation(@Param("des") String designation);

    @Query("select p from Product p where p.prixAchat like :price")
    Product findByPrixAchat(@Param("price") Double prixAchat);

    @Query("select p from Product p where p.subCategory.id =:scat")
    List<Product> findProductByScateoryId(@Param("scat") Long scatId);

    @Query("select (p.subCategory.libelle), count(p) from Product p where (p.qtestock > p.stockInitial) group by(p.subCategory)")
    List<?> countNumberOfProductWithStoc();

    @Query("select count(p) from Product p where (p.qtestock  > p.stockInitial) ")
    Integer countNumbersOfProductsByStock();

    @Query("select count(p) from Product p where (p.qtestock = p.stockInitial) ")
    Integer countNumbersOfProductsWhenQStockEqualStockInit();

    @Query("select count(p) from Product p where (p.stockInitial > p.qtestock) ")
    Integer countNumbersOfProductsWhenQStockInfStockInit();

    @Query("select p from Product p where p.designation like :des")
    List<Product> findListProductByDesignation(@Param("des") String designation);

    List<Product> findByOrderByIdDesc();

    Optional<Product> findByBarCode(String barCode);

    @Query("select p from Product p order by designation Asc")
    List<Product> findListProductByOrderByDesignationAsc();
}
