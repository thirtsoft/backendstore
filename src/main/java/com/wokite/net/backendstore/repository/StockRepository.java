package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("select p from Stock p where p.quantite like :qte")
    Stock findByQuantite(@Param("qte") int quantite);

    @Query("select p from Stock p where p.product.id =:prod")
    Stock findStockByProductId(@Param("prod") Long prodId);

    @Query("select p from Stock p where p.product.id =:prod")
    List<Stock> findListStockByProductId(@Param("prod") Long prodId);

}
