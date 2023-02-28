package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.Creance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CreanceRepository extends JpaRepository<Creance, Long> {

    @Modifying()
    @Query("update Creance c set c.status = :status where c.id = :id")
    void updateCreanceStatus(@Param("status") String status, @Param("id") Long id);

    @Modifying
    @Query("update Creance c set c.status = :status where c.id = :id")
    void setCreanceStatusById(@Param("status") String status, @Param("id") Long id);

    @Query("select count(p) from Creance p ")
    Integer countNumberOfCreance();

    @Query("select sum(c.totalCreance) from Creance c")
    BigDecimal countNumbersOfCreances();

    @Query("select sum((c.totalCreance)-(c.avanceCreance)) from Creance c where year(c.dateCreance) = year(current_date)")
    BigDecimal sumTotalOfCreanceByYear();

    @Query("select EXTRACT(month from(c.dateCreance)), sum(c.totalCreance) from Creance c group by EXTRACT(month from(c.dateCreance))")
    List<?> sumTotalOfCreancesByMonth();

    @Query("select p from Creance p where p.client.id =:cl")
    List<Creance> ListCreanceClientByClientId(@Param("cl") Long clientId);

    @Query("select p from Creance p where p.client.id =:cl and p.status!='PAYEE' order by id Desc")
    List<Creance> ListCreanceClientByClientIdAndStatus(@Param("cl") Long clientId);

    List<Creance> findByOrderByIdDesc();

    @Query("select v from Creance v where (v.dateCreance <= current_date - 90) ORDER BY v.dateCreance DESC ")
    List<Creance> findListCreanceOf3LatestMonth();

    List<Creance> findTop500ByOrderByIdDesc();

}
