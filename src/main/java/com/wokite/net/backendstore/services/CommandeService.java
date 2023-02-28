package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Commande;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CommandeService {

    Commande saveCommande(Commande commande);

    Commande createCommande(Commande commande);

    ResponseEntity<String> createOrder(Commande commande);

    Commande updateCommande(Long comId, Commande commande);

    Optional<Commande> findCommandeById(Long comId);

    Commande findByNumeroCommande(Long numeroCommande);

    int getNombreCommandes(Date d1, Date d2);

    int getNumberOfCommande();

    Long countNumbersOfCommandes();

    BigDecimal sumTotalOfCommandesInMonth();

    BigDecimal sumTotalOfCommandesInYear();

    List<Commande> findAllCommande();

    List<Commande> findAllCommandesOrderDesc();

    List<Commande> findListCommandesOf3LatestMonth();

    List<Commande> findTop500OCommandesOrderByIdDesc();

    List<Commande> findCommandesByClientId(Long clientId);

    List<Commande> findCommandesByDate(Date dateCommande);

    List<?> countNumberTotalOfCommandePeerMonth();

    List<?> sumTotalOfCommandePeerMonth();

    ResponseEntity<Object> deleteCommandeClient(Long id);

    void deleteCommande(Long id);


}
