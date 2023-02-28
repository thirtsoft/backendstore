package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Creance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CreanceService {

    Creance saveCreance(Creance creance);

    Creance updateCreance(Long id, Creance creance);

    void updateCreanceStatus(Long id, String status);

    boolean updateStatus(Long reference, String status);

    boolean updateStatusCreance(String codeCreance, String status);

    Creance setCreanceOnlyAvanceCreance(double avanceCreance, String id);

    Creance setCreanceOnlyStatus(String status, String id);

    Optional<Creance> findCreanceById(Long creanceId);

    List<Creance> findAllCreances();

    List<Creance> findAllCreancesOrderDesc();

    List<Creance> findListCreanceOf3LatestMonth();

    List<Creance> findTop500OCreanceOrderByIdDesc();

    List<Creance> findCreanceByClientId(Long clientId);

    List<Creance> ListCreanceClientByClientIdAndStatus(Long clientId);

    int getNumberOfCreances();

    BigDecimal countNumbersOfCreances();

    BigDecimal sumTotalOfCreanceInYear();

    List<?> sumTotalOfCreancesPeerMonth();

    void deleteCreance(Long id);

}
