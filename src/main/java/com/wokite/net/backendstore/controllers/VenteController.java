package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.VenteApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueVente;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.models.Vente;
import com.wokite.net.backendstore.services.HistoriqueVenteService;
import com.wokite.net.backendstore.services.UtilisateurService;
import com.wokite.net.backendstore.services.VenteService;
import com.wokite.net.backendstore.utils.GeneratedNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class VenteController implements VenteApi {

    @Autowired
    private VenteService venteService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private HistoriqueVenteService historiqueVenteService;

    @Override
    public ResponseEntity<Vente> saveVente(Vente vente) {
        vente.setNumeroVente(GeneratedNumber.generateCodeCommand());
        Vente ventedResultant = venteService.saveVente(vente);
        return new ResponseEntity<>(ventedResultant, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Vente> createVente(Vente vente, Long id) {
        Utilisateur userInfo = utilisateurService.findUtilisateurById(id).get();
        vente.setUtilisateur(userInfo);
        vente.setNumeroVente(GeneratedNumber.generateCodeCommand());
        Vente ventedResultant = venteService.saveVente(vente);
/*
        HistoriqueVente historiqueVente = new HistoriqueVente();
        historiqueVente.setUtilisateur(userInfo);
        historiqueVente.setVente(ventedResultant);
        historiqueVente.setAction("AJOUT VENTE");
        historiqueVente.setCreatedDate(new Date());
        historiqueVenteService.saveHistoriqueVente(historiqueVente);
*/
        return new ResponseEntity<>(ventedResultant, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Vente> createVenteWithBarcode(Vente vente, Long id) {

        Utilisateur userInfo = utilisateurService.findUtilisateurById(id).get();

        vente.setUtilisateur(userInfo);
        vente.setNumeroVente(GeneratedNumber.generateCodeCommand());

        Vente ventedResultant = venteService.saveVenteWithBarcode(vente);

        HistoriqueVente historiqueVente = new HistoriqueVente();

        historiqueVente.setUtilisateur(userInfo);
        historiqueVente.setVente(ventedResultant);
        historiqueVente.setAction("AJOUT VENTE");
        historiqueVente.setCreatedDate(new Date());

        historiqueVenteService.saveHistoriqueVente(historiqueVente);

        return new ResponseEntity<>(ventedResultant, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Vente> updateVente(Long id, Vente vente) {

        Utilisateur userInfo = utilisateurService.findUtilisateurById(id).get();

        vente.setId(id);
        Vente ventedResultant = venteService.saveVente(vente);

        HistoriqueVente historiqueVente = new HistoriqueVente();

        historiqueVente.setUtilisateur(userInfo);
        historiqueVente.setVente(ventedResultant);
        historiqueVente.setAction("MODIFICATION VENTE");
        historiqueVente.setCreatedDate(new Date());

        historiqueVenteService.saveHistoriqueVente(historiqueVente);

        return new ResponseEntity<>(ventedResultant, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Vente> getVenteById(Long id) throws ResourceNotFoundException {
        Vente vente = venteService.findVenteById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vente Not found"));
        return ResponseEntity.ok().body(vente);
    }

    @Override
    public ResponseEntity<List<Vente>> getAllVentes() {
        List<Vente> ventedList = venteService.findAllVentes();
        return new ResponseEntity<>(ventedList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Vente>> getAllVentesOrderDesc() {
        List<Vente> ventedList = venteService.findAllVentesOrderDesc();
        return new ResponseEntity<>(ventedList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Vente>> getAllVenteOf3LatestMonths() {
        List<Vente> ventedList = venteService.findListVenteOf3LatestMonth();
        return new ResponseEntity<>(ventedList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Vente>> getTop500OVenteOrderByIdDesc() {
        List<Vente> ventedList = venteService.findTop500OVenteOrderByIdDesc();
        return new ResponseEntity<>(ventedList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Vente>> getVenteWithParticularDayAndMonth() {
        List<Vente> ventedList = venteService.findVenteWithParticularDayAndMonth();
        return new ResponseEntity<>(ventedList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Vente>> getListOfVenteByEmployeId(Long empId) {
        List<Vente> ventedList = venteService.findListVenteByEmployeId(empId);
        return new ResponseEntity<>(ventedList, HttpStatus.OK);
    }

    @Override
    public Long getNumberOfVentes() {
        return venteService.getNumberOfVente();
    }

    @Override
    public int getNumberOfVenteInDay() {
        return venteService.countNumberOfVenteInDay();
    }

    @Override
    public BigDecimal getSumsOfVenteInDay() {
        return venteService.sumTotalOfVenteInDay();
    }

    @Override
    public BigDecimal sumTotalOfVentesInMonth() {
        return venteService.sumTotalOfVentesInMonth();
    }

    @Override
    public BigDecimal sumTotalOfVentesInYear() {
        return venteService.sumTotalOfVentesInYear();
    }

    @Override
    public BigDecimal getSumsOfVentes() {
        return venteService.countSumsOfVentess();
    }

    @Override
    public List<?> getNumberTotalOfVentePeerMonth() {
        return venteService.countNumberTotalOfVentePeerMonth();
    }

    @Override
    public List<?> getSumTotalOfVentePeerMonth() {
        return venteService.sumTotalOfVentePeerMonth();
    }

    @Override
    public List<?> getSumTotalOfVentePeerYear() {
        return venteService.sumTotalOfVentePeerYears();
    }

    @Override
    public ResponseEntity<?> deleteVente(Long id) {
        venteService.deleteVente(id);
        return ResponseEntity.ok()
                .build();
    }
}
