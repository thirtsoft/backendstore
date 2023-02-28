package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.LigneVente;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface LigneVenteService {

    LigneVente saveLigneVente(LigneVente ligneVente);

    LigneVente updateLigneVente(Long lventeId, LigneVente ligneVente);

    Optional<LigneVente> findLigneVenteById(Long lventeId);

    List<LigneVente> findAllLigneVentes();

    List<LigneVente> findAllLigneVentesOrderDesc();

    List<LigneVente> findLigneVenteByProduitId(Long prodId);

    List<LigneVente> findLigneVenteByVenteId(Long venteId);

    ResponseEntity<Object> deleteLigneVente(Long ligneVenteId);

    List<LigneVente> findTop200ByOrderByIdDesc();

    void deleteLventeByNumero(Long numero);


}
