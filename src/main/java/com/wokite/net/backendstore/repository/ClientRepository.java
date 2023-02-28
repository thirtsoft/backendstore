package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c.raisonSocial, count(c) as countClient from Client c group by c.raisonSocial")
    List<Object[]> ListClientGroupByRaisonSocial();

    List<Client> findByOrderByIdDesc();

}
