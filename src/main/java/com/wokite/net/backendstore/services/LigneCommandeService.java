package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.LigneCommande;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface LigneCommandeService {

    LigneCommande saveLigneCommande(LigneCommande ligneCommande);

    List<LigneCommande> saveListLigneCmd(List<LigneCommande> ligneCommandeList);

    LigneCommande updateLigneCommande(Long lCmdId, LigneCommande ligneCommande);

    Optional<LigneCommande> findLigneCommandeById(Long lCmdId);

    List<LigneCommande> findAllLigneCommande();

    List<LigneCommande> findAllLigneCommandesOrderDesc();

    List<LigneCommande> findLigneCommandeByProductId(Long prodId);

    List<LigneCommande> findLigneCommandeByCommandeClientId(Long comId);

    List<LigneCommande> findAllLcomByNumero(Long numero);

    ResponseEntity<Object> deleteLigneCommande(Long id);

    void deleteLcomByNumero(Long numero);

}
