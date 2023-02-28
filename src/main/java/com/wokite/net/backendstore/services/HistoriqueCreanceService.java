package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.HistoriqueCreance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface HistoriqueCreanceService {

    HistoriqueCreance saveHistoriqueCreance(HistoriqueCreance historiqueCreance);

    HistoriqueCreance saveHistoriqueCommandeWithConnectedUserAndCreanceID(HistoriqueCreance historiqueCreance,
                                                                          Long userId, Long creanceId);

    Optional<HistoriqueCreance> findHistoriqueCreanceById(Long id);

    List<HistoriqueCreance> findAllHistoriqueCreances();

    List<HistoriqueCreance> findAllHistoriqueCreancesOrderDesc();

    BigDecimal countNumberOfHistoriqueCreances();

    void deleteHistoriqueCreance(Long id);
}
