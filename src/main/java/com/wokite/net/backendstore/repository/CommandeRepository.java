package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.Client;
import com.wokite.net.backendstore.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("select count(p) from Commande p where p.dateCommande between :d1 and :d2")
    Integer countBetween(@Param("d1") Date d1, @Param("d2") Date d2);

    @Query("select sum(c.totalCommande) from Commande c")
    BigDecimal countNumbersOfCommandes();

    @Query("select sum(c.totalCommande) from Commande c where month(c.dateCommande) = month(current_date)")
    BigDecimal sumTotalOfCommandesByMonth();

    @Query("select sum(c.totalCommande) from Commande c where year(c.dateCommande) = year(current_date)")
    BigDecimal sumTotalOfCommandesByYear();

    @Query("select p from Commande p where p.numeroCommande like :num")
    Commande findByNumeroCommande(@Param("num") Long numeroCommande);

    @Query("select p from Commande p where p.status like :status")
    Commande findByStatus(@Param("status") String status);

    @Query("select c from Commande c where c.status like :status")
    List<Commande> ListCommandeByStatus(@Param("status") String status);

    List<Commande> findAllByDateCommande(Date dateCommande);

    @Query("select v from Commande v where (v.dateCommande <= current_date - 90) ORDER BY v.dateCommande DESC ")
    List<Commande> findListCommandeOf3LatestMonth();

    List<Commande> findTop500ByOrderByIdDesc();

    @Query("select EXTRACT(month from(c.dateCommande)), count(c) from Commande c group by EXTRACT(month from(c.dateCommande))")
    List<?> countNumberOfCommandeByMonth();

    @Query("select EXTRACT(month from(c.dateCommande)), sum(c.totalCommande) from Commande c group by EXTRACT(month from(c.dateCommande))")
    List<?> sumTotalOfCommandeByMonth();

    @Query("select p from Commande p where p.client.id =:cl")
    List<Commande> ListCommandeByClientId(@Param("cl") Long clientId);

    List<Commande> findByOrderByIdDesc();


}
