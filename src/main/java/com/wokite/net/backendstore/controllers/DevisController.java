package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.DevisApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Devis;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.services.DevisService;
import com.wokite.net.backendstore.services.UtilisateurService;
import com.wokite.net.backendstore.utils.GeneratedNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class DevisController implements DevisApi {

    private final Double total = 0.0;
    @Autowired
    private DevisService devisService;
    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public ResponseEntity<Devis> createDevis(Devis devis, Long id) {

        Utilisateur utilisateur = utilisateurService.findUtilisateurById(id).get();

        devis.setUtilisateur(utilisateur);
        devis.setNumeroDevis(GeneratedNumber.generateCodeCommand());

        Devis devsResult = devisService.saveDevis(devis);

        return new ResponseEntity<>(devsResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Devis> getDevisById(Long id) {
        Devis devisResult = devisService.findDevisById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Devis that id is" + id + "not found"));
        return new ResponseEntity<>(devisResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Devis> updateDevis(Long id, Devis devis) throws Exception {
        devis.setId(id);
        Devis devisResult = devisService.saveDevis(devis);
        return new ResponseEntity<>(devisResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Devis>> getAllDevis() {
        List<Devis> devisList = devisService.findAllDevis();
        return new ResponseEntity<>(devisList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Devis>> getAllDevisOrderDesc() {
        List<Devis> devisList = devisService.findAllDevissOrderDesc();
        return new ResponseEntity<>(devisList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Devis>> getDevisByClientId(Long clientId) {
        List<Devis> devisList = devisService.findDevisByClientId(clientId);
        return new ResponseEntity<>(devisList, HttpStatus.OK);
    }

    @Override
    public int getNumberOfDevis() {
        return devisService.getNumberOfDevis();
    }

    @Override
    public BigDecimal getNumbersOfDevis() {
        return devisService.countNumbersOfDevis();
    }

    @Override
    public List<?> getNumberTotalOfDevisPeerMonth() {
        return devisService.countNumberTotalOfDevisPeerMonth();
    }

    @Override
    public List<?> sumTotalOfDevisPeerMonth() {
        return devisService.sumTotalOfDevisPeerMonth();
    }

    @Override
    public ResponseEntity<?> deleteDevis(Long id) {
        devisService.deleteDevis(id);
        return ResponseEntity.ok().build();
    }
}
