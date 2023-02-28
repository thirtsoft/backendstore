package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.LigneApprovisionnement;

import java.util.List;
import java.util.Optional;

public interface LigneApprovisionnementService {

    LigneApprovisionnement saveLigneApprovisionnement(LigneApprovisionnement ligneApprovisionnement);

    LigneApprovisionnement updateLigneApprovisionnement(Long lApproId, LigneApprovisionnement ligneApprovisionnement);

    Optional<LigneApprovisionnement> findLigneApprovisionnementById(Long lApproId);

    List<LigneApprovisionnement> findAllLigneApprovisionnements();

    List<LigneApprovisionnement> findAllLigneApprovisionnementsOrderDesc();

    List<LigneApprovisionnement> findAllLApproByNumero(Long numero);

    List<LigneApprovisionnement> findListLigneApprovisionnementByProductId(Long prodId);

    List<LigneApprovisionnement> findListLigneApprovisionnementByApprovisionnementId(Long approId);

    void deleteLApproByNumero(Long numero);

}
