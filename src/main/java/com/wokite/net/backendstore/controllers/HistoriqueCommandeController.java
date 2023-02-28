package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.HistoriqueCommandeApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueCommande;
import com.wokite.net.backendstore.services.HistoriqueCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class HistoriqueCommandeController implements HistoriqueCommandeApi {

    @Autowired
    private HistoriqueCommandeService historiqueCommandeService;

    @Override
    public ResponseEntity<HistoriqueCommande> createHistoriqueCommande(HistoriqueCommande historiqueCommande) {
        HistoriqueCommande historiqueCommandeResult = historiqueCommandeService.saveHistoriqueCommande(historiqueCommande);
        return new ResponseEntity<>(historiqueCommandeResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueCommande> getHistoriqueCommandeById(Long id) throws ResourceNotFoundException {
        HistoriqueCommande historiqueCommandeResult = historiqueCommandeService.findHistoriqueCommandeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historique Commande " + id + "not found"));
        return new ResponseEntity<>(historiqueCommandeResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueCommande>> getAllHistoriqueCommandes() {
        List<HistoriqueCommande> historiqueCommandeList = historiqueCommandeService.findAllHistoriqueCommandes();
        return new ResponseEntity<>(historiqueCommandeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueCommande>> getAllHistoriqueCommandesOrderDesc() {
        List<HistoriqueCommande> historiqueCommandeList = historiqueCommandeService.findAllHistoriqueCommandesOrderDesc();
        return new ResponseEntity<>(historiqueCommandeList, HttpStatus.OK);
    }

    @Override
    public BigDecimal getNumberOfHistoriqueCommande() {
        return historiqueCommandeService.countNumberOfHistoriqueCommandes();
    }

    @Override
    public ResponseEntity<?> deleteHistoriqueCommande(Long id) {
        historiqueCommandeService.deleteHistoriqueCommande(id);
        return ResponseEntity.ok()
                .build();
    }
}
