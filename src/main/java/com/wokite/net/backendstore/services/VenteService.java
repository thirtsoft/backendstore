package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Vente;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VenteService {

    Vente saveVente(Vente vente);

    Vente saveVenteWithBarcode(Vente vente);

    Vente updateVente(Long venteId, Vente vente);

    Optional<Vente> findVenteById(Long venteId);

    Vente findVenteByNumeroVente(Long numeroVente);

    List<Vente> findAllVentes();

    List<Vente> findAllVentesOrderDesc();

    List<Vente> findListVenteOf3LatestMonth();

    List<Vente> findTop500OVenteOrderByIdDesc();

    List<Vente> findVenteWithParticularDayAndMonth();

    List<Vente> findListVenteByEmployeId(Long empId);

    Integer countNumberOfVenteInDay();

    int getNombreVentes(Date d1, Date d2);

    Long getNumberOfVente();

    long generateNumeroVente();

    BigDecimal countSumsOfVentess();

    BigDecimal sumTotalOfVenteInDay();

    BigDecimal sumTotalOfVentesInMonth();

    BigDecimal sumTotalOfVentesInYear();

    List<?> countNumberTotalOfVentePeerMonth();

    List<?> sumTotalOfVentePeerMonth();

    List<?> sumTotalOfVentePeerYears();

    ResponseEntity<Object> deleteVenteClient(Long id);

    void deleteVente(Long id);
}
