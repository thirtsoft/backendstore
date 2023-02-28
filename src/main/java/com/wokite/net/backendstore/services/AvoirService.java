package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Avoir;

import java.util.List;
import java.util.Optional;

public interface AvoirService {

    Avoir saveAvoir(Avoir avoir);

    Avoir updateAvoir(Long id, Avoir avoir);

    Optional<Avoir> findAvoirById(Long id);

    List<Avoir> findAllAvoirs();

    List<Avoir> findAllAvoirsOrderDesc();

    List<Avoir> findAvoirsByFournisseurId(Long fourId);

    void deleteAvoir(Long id);


}
