package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.HistoriqueVente;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface HistoriqueVenteService {

    HistoriqueVente saveHistoriqueVente(HistoriqueVente historiqueVente);

    HistoriqueVente saveHistoriqueVenteWithConnectedUserAndVenteID(HistoriqueVente historiqueVente,
                                                                   Long userId, Long venteId);

    Optional<HistoriqueVente> findHistoriqueVenteById(Long id);

    List<HistoriqueVente> findAllHistoriqueVentes();

    List<HistoriqueVente> findAllHistoriqueVentesOrderDesc();

    BigDecimal countNumberOfHistoriqueVentes();

    void deleteHistoriqueVente(Long id);
}
