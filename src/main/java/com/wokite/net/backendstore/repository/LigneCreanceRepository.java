package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.LigneCreance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneCreanceRepository extends JpaRepository<LigneCreance, Long> {

    List<LigneCreance> findAllByNumeroLigneCreance(Long numeroLigneCreance);

    @Modifying
    @Query("delete from LigneCreance where numeroLigneCreance = :numero")
    void deleteByNumeroLigneCreance(@Param("numero") Long numeroLigneCreance);

    @Query("select p from LigneCreance p where p.product.id =:prod")
    List<LigneCreance> ListLigneCreanceByProductId(@Param("prod") Long prodId);

    @Query("select p from LigneCreance p where p.creance.id =:num")
    List<LigneCreance> ListLigneCreanceByCreanceId(@Param("num") Long creanceId);

    List<LigneCreance> findByOrderByIdDesc();
}
