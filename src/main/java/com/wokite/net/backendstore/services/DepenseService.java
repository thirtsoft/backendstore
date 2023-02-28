package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Depense;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DepenseService {

    Depense saveCharge(Depense depense);

    Depense updateCharge(Long idCh, Depense depense);

    Optional<Depense> findChargeById(Long idCh);

    BigDecimal sumTotalOfChargeInYear();

    List<Depense> findAllCharges();

    List<Depense> findAllChargesOrderDesc();

    List<?> sumTotalOfChargePeerMonth();

    void deleteCharge(Long id);
}
