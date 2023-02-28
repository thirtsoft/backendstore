package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Devis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DevisService {

    Devis saveDevis(Devis devis);

    Devis updateDevis(Long devId, Devis devis);

    Optional<Devis> findDevisById(Long devId);

    BigDecimal countNumbersOfDevis();

    int getNombreDevis(Date d1, Date d2);

    int getNumberOfDevis();

    List<Devis> findAllDevis();

    List<Devis> findAllDevissOrderDesc();

    List<Devis> findDevisByClientId(Long clientId);

    List<Devis> findDevisByDate(Date dateDevis);

    List<?> countNumberTotalOfDevisPeerMonth();

    List<?> sumTotalOfDevisPeerMonth();

    void deleteDevis(Long id);

}
