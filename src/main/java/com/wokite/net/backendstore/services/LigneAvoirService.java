package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.LigneAvoir;

import java.util.List;
import java.util.Optional;

public interface LigneAvoirService {

    LigneAvoir saveLigneAvoir(LigneAvoir ligneAvoir);

    LigneAvoir updateLigneAvoir(Long lAvoirId, LigneAvoir ligneAvoir);

    Optional<LigneAvoir> findLigneAvoirById(Long lAvoirId);

    List<LigneAvoir> findAllLigneAvoirs();

    List<LigneAvoir> findAllLigneAvoirsOrderDesc();

    List<LigneAvoir> findAllLApproByNumero(Long numero);

    List<LigneAvoir> findListLigneAvoirByProductId(Long prodId);

    List<LigneAvoir> findListLigneAvoirByAvoirId(Long approId);

    void deleteLAVoirByNumero(Long numero);

}
