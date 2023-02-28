package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.HistoriqueVenteApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueVente;
import com.wokite.net.backendstore.services.HistoriqueVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class HistoriqueVenteController implements HistoriqueVenteApi {

    @Autowired
    private HistoriqueVenteService historiqueVenteService;

    @Override
    public ResponseEntity<HistoriqueVente> createHistoriqueVente(HistoriqueVente historiqueVente) {
        HistoriqueVente historiqueVenteResult = historiqueVenteService.saveHistoriqueVente(historiqueVente);
        return new ResponseEntity<>(historiqueVenteResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueVente> getHistoriqueVenteById(Long id) throws ResourceNotFoundException {
        HistoriqueVente historiqueVenteResult = historiqueVenteService.findHistoriqueVenteById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historique Commande " + id + "not found"));
        return new ResponseEntity<>(historiqueVenteResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueVente>> getAllHistoriqueVente() {
        List<HistoriqueVente> historiqueVenteResult = historiqueVenteService.findAllHistoriqueVentes();
        return new ResponseEntity<>(historiqueVenteResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueVente>> getAllHistoriqueVenteOrderDesc() {
        List<HistoriqueVente> HistoriqueVenteList = historiqueVenteService.findAllHistoriqueVentesOrderDesc();
        return new ResponseEntity<>(HistoriqueVenteList, HttpStatus.OK);
    }

    @Override
    public BigDecimal getNumberOfHistoriqueVente() {
        return historiqueVenteService.countNumberOfHistoriqueVentes();
    }


    @Override
    public ResponseEntity<?> deleteHistoriqueVente(Long id) {
        historiqueVenteService.deleteHistoriqueVente(id);
        return ResponseEntity.ok()
                .build();
    }


}
