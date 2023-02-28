package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.LigneCreance;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface LigneCreanceService {

    LigneCreance saveLigneCreance(LigneCreance ligneCreance);

    LigneCreance updateLigneCreance(Long lCreanceId, LigneCreance ligneCreance);

    Optional<LigneCreance> findLigneCreanceById(Long lCreanceId);

    List<LigneCreance> findAllLigneCreances();

    List<LigneCreance> findAllLigneCreancesOrderDesc();

    List<LigneCreance> findLigneCreanceByCreanceId(Long creanceId);

    List<LigneCreance> findLigneCreanceByProductId(Long prodId);

    List<LigneCreance> findAllLcreanceByNumero(Long numero);

    ResponseEntity<Object> deleteLigneCreance(Long creanceId);

    void deleteLcreanceByNumero(Long numero);


}
