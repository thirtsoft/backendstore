package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.HistoriqueDepense;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface HistoriqueDepenseService {

    HistoriqueDepense saveHistoriqueCharge(HistoriqueDepense historiqueDepense);

    HistoriqueDepense saveHistoriqueChargeWithConnectedUserAndChargeID(HistoriqueDepense historiqueDepense,
                                                                       Long userId, Long chargeId);

    Optional<HistoriqueDepense> findHistoriqueChargeById(Long id);

    List<HistoriqueDepense> findAllHistoriqueCharges();

    List<HistoriqueDepense> findAllHistoriqueChargesOrderDesc();

    BigDecimal countNumberOfHistoriqueCharges();

    void deleteHistoriqueCharge(Long id);
}
