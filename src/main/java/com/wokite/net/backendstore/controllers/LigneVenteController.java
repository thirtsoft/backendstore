package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.LigneVenteApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneVente;
import com.wokite.net.backendstore.services.LigneVenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class LigneVenteController implements LigneVenteApi {

    private final LigneVenteService ligneVenteService;

    public LigneVenteController(LigneVenteService ligneVenteService) {
        this.ligneVenteService = ligneVenteService;
    }

    @Override
    public ResponseEntity<LigneVente> createLigneVente(LigneVente ligneVente) {
        LigneVente ligneVenteResult = ligneVenteService.saveLigneVente(ligneVente);
        return new ResponseEntity<>(ligneVenteResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LigneVente> updateLigneVente(Long lventeId, LigneVente ligneVente) {
        ligneVente.setId(lventeId);
        LigneVente ligneVenteResult = ligneVenteService.saveLigneVente(ligneVente);
        return new ResponseEntity<>(ligneVenteResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LigneVente> getLigneVenteById(Long id) throws ResourceNotFoundException {
        LigneVente ligneVenteResult = ligneVenteService.findLigneVenteById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneVente " + id + "not found"));
        return new ResponseEntity<>(ligneVenteResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneVente>> getAllLigneVentes() {
        List<LigneVente> ligneVenteList = ligneVenteService.findAllLigneVentes();
        return new ResponseEntity<>(ligneVenteList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneVente>> getAllLigneVenteOrderDesc() {
        List<LigneVente> ligneVenteList = ligneVenteService.findAllLigneVentesOrderDesc();
        return new ResponseEntity<>(ligneVenteList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneVente>> getListLigneVenteByProduitId(Long prodId) {
        List<LigneVente> ligneVenteList = ligneVenteService
                .findLigneVenteByProduitId(prodId);
        return new ResponseEntity<>(ligneVenteList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneVente>> getListLigneVenteByVenteId(Long venteId) {
        List<LigneVente> ligneVenteList = ligneVenteService
                .findLigneVenteByVenteId(venteId);
        return new ResponseEntity<>(ligneVenteList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneVente>> getTop200ItemsOrderByIdDesc() {
        List<LigneVente> ligneVenteList = ligneVenteService.findTop200ByOrderByIdDesc();
        return new ResponseEntity<>(ligneVenteList, HttpStatus.OK);
    }
}
