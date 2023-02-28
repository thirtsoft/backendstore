package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.LigneDevis;

import java.util.List;
import java.util.Optional;

public interface LigneDevisService {

    LigneDevis saveLigneDevis(LigneDevis ligneDevis);

    List<LigneDevis> saveListLigneDevis(List<LigneDevis> ligneDevis);

    LigneDevis updateLigneDevis(Long ldevId, LigneDevis ligneDevis);

    Optional<LigneDevis> findLigneDevisById(Long ldevId);

    List<LigneDevis> findAllLigneDevis();

    List<LigneDevis> findAllLigneDevissOrderDesc();

    List<LigneDevis> findLigneDevisByProductId(Long prodId);

    List<LigneDevis> findLigneDevisByDevId(Long devId);

    List<LigneDevis> findAllLigneDevisByNumero(Long numero);

    void deleteLigneDevis(Long id);

    void deleteLigneDevisByNumero(Long numero);

}
