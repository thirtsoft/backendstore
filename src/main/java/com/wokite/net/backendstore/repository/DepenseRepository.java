package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long> {

    @Query("select sum(c.montantCharge) from Depense c where year(c.dateCharge) = year(current_date)")
    BigDecimal sumTotalOfChargeByYear();

    @Query("select EXTRACT(month from(v.dateCharge)), sum(v.montantCharge) from Depense v group by EXTRACT(month from(v.dateCharge))")
    List<?> sumTotalOfChargeByMonth();

    List<Depense> findByOrderByIdDesc();

}
