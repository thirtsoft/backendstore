package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.HistoriqueDepense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HistoriqueDepenseRepository extends JpaRepository<HistoriqueDepense, Long> {

    @Query("select count(f) from HistoriqueDepense f ")
    BigDecimal countNumberOfHistoriqueCharges();

    List<HistoriqueDepense> findByOrderByIdDesc();

}
