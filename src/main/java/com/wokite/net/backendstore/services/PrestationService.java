package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Prestation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PrestationService {

    Prestation savePrestation(Prestation prestation);

    Prestation updatePrestation(Long prestId, Prestation prestation);

    Optional<Prestation> findPrestationById(Long prestId);

    List<Prestation> findAllPrestations();

    List<Prestation> findAllPrestationsOrderDesc();

    BigDecimal sumTotalOfPrestationInMonth();

    BigDecimal sumTotalOfPrestationInYear();

    void deletePrestation(Long prestId);

}
