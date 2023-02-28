package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Versement;

import java.util.List;
import java.util.Optional;

public interface VersementService {

    Versement saveVersement(Versement versement);

    Versement updateVersement(Long id, Versement versement);

    Optional<Versement> findVersementById(Long id);

    List<Versement> findAllVersements();

    List<Versement> findAllVersementsOrderDesc();

    List<Versement> findVersementByEmployeId(Long empId);

    void deleteVersement(Long id);

}
