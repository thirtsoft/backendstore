package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Approvisionnement;

import java.util.List;
import java.util.Optional;

public interface ApprovisionnementService {

    Approvisionnement saveApprovisionnement(Approvisionnement approvisionnement);

    Approvisionnement updateApprovisionnement(Long approId, Approvisionnement approvisionnement);

    Approvisionnement updateStatusAppro(String status, String id);

    Approvisionnement updateMontantAvanceAppro(double montantAvance, String id);

    Optional<Approvisionnement> findApprovisionnementById(Long approId);

    Approvisionnement findApprovisionnementByCode(Long code);

    List<Approvisionnement> findAllApprovisionnements();

    List<Approvisionnement> findAllApprovisionnementsOrderDesc();

    List<Approvisionnement> findListApprovisionnementOf3LatestMonth();

    List<Approvisionnement> findTop500OApprovisionnementOrderByIdDesc();

    List<Approvisionnement> findListApprovisionnementByFournisseurId(Long fourId);

    void deleteAppro(Long id);


}
