package com.wokite.net.backendstore.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wokite.net.backendstore.controllers.api.CreanceApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Creance;
import com.wokite.net.backendstore.models.HistoriqueCreance;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.services.CreanceService;
import com.wokite.net.backendstore.services.HistoriqueCreanceService;
import com.wokite.net.backendstore.services.LigneCreanceService;
import com.wokite.net.backendstore.services.UtilisateurService;
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
public class CreanceController implements CreanceApi {

    @Autowired
    private CreanceService creanceService;

    @Autowired
    private LigneCreanceService ligneCreanceService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private HistoriqueCreanceService historiqueCreanceService;


    @Override
    public ResponseEntity<Creance> createCreance(Creance creance /*, Long id*/) {
        Creance creanceResultat;


        HistoriqueCreance historiqueCreance = new HistoriqueCreance();

    //    Utilisateur utilisateur = utilisateurService.findUtilisateurById(id).get();
    //    creance.setUtilisateur(utilisateur);
        creance.setReference(GeneratedNumber.generateCodeCommand());

        creanceResultat = creanceService.saveCreance(creance);
/*
        historiqueCreance.setUtilisateur(utilisateur);
        historiqueCreance.setCreance(creanceResultat);
        historiqueCreance.setAction("AJOUT CREANCE");
        historiqueCreance.setCreatedDate(new Date());

        historiqueCreanceService.saveHistoriqueCreance(historiqueCreance);
        */

        return new ResponseEntity<>(creanceResultat, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Creance> getCreanceById(Long id) {
        Creance creanceResult = creanceService.findCreanceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Creance that id is" + id + "not found"));
        return new ResponseEntity<>(creanceResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Creance> updateCreance(Long id, Creance creance) throws Exception {

        Creance creanceResultat;

        HistoriqueCreance historiqueCreance = new HistoriqueCreance();

    //    Utilisateur utilisateur = utilisateurService.findUtilisateurById(id).get();
    //    creance.setUtilisateur(utilisateur);
        creance.setReference(GeneratedNumber.generateCodeCommand());

        creance.setId(id);
        creanceResultat = creanceService.saveCreance(creance);
/*
        historiqueCreance.setUtilisateur(utilisateur);
        historiqueCreance.setCreance(creanceResultat);
        historiqueCreance.setAction("MODIFICATION CREANCE");
        historiqueCreance.setCreatedDate(new Date());

        historiqueCreanceService.saveHistoriqueCreance(historiqueCreance);


 */
        return new ResponseEntity<>(creanceResultat, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> updateStatus(ObjectNode json) {
        Long reference;
        String ref;
        String status;
        try {
            ref = new ObjectMapper().treeToValue(json.get("ref"), String.class);
            reference = Long.parseLong(ref);
            status = new ObjectMapper().treeToValue(json.get("status"), String.class);
            boolean test = this.creanceService.updateStatus(reference, status);
            if (test)
                return new ResponseEntity<>(test, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            System.out.println("Parsing Exception!!");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Boolean> updateStatusOfCreance(ObjectNode json) {
        String codeCreance;
        String status;
        try {
            codeCreance = new ObjectMapper().treeToValue(json.get("codeCreance"), String.class);
            status = new ObjectMapper().treeToValue(json.get("status"), String.class);
            boolean test = this.creanceService.updateStatusCreance(codeCreance, status);
            if (test)
                return new ResponseEntity<>(test, HttpStatus.OK);

        } catch (JsonProcessingException e) {
            System.out.println("Parsing Exception!!");
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Creance> updateStatusOfCreanceById(Long id, Creance creance) {
        Creance creanceResultat;

        HistoriqueCreance historiqueCreance = new HistoriqueCreance();

   //     Utilisateur utilisateur = utilisateurService.findUtilisateurById(id).get();
    //    creance.setUtilisateur(utilisateur);

        creance.setId(id);

        creanceResultat = creanceService.saveCreance(creance);

        /*
        historiqueCreance.setUtilisateur(utilisateur);
        historiqueCreance.setCreance(creanceResultat);
        historiqueCreance.setAction("MODIFICATION STATUS CREANCE");
        historiqueCreance.setCreatedDate(new Date());

        historiqueCreanceService.saveHistoriqueCreance(historiqueCreance);
        */

        return new ResponseEntity<>(creanceResultat, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateOnlyStatusOfCreance(String status, String id) {

        HistoriqueCreance historiqueCreance = new HistoriqueCreance();
/*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();*/

        Utilisateur utilisateur = utilisateurService.findUtilisateurById(Long.parseLong(id)).get();


        Creance newCreance = creanceService.setCreanceOnlyStatus(status, id);

        historiqueCreance.setUtilisateur(utilisateur);
        historiqueCreance.setCreance(newCreance);
        historiqueCreance.setAction("MODIFICATION STATUS CREANCE");
        historiqueCreance.setCreatedDate(new Date());
        historiqueCreanceService.saveHistoriqueCreance(historiqueCreance);

        return new ResponseEntity<>(newCreance, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateOnlyAvanceOfCreance(double avanceCreance, String id) {

        HistoriqueCreance historiqueCreance = new HistoriqueCreance();
/*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();*/

        Utilisateur utilisateur = utilisateurService.findUtilisateurById(Long.parseLong(id)).get();

        Creance newCreance = creanceService.setCreanceOnlyAvanceCreance(avanceCreance, id);

        historiqueCreance.setUtilisateur(utilisateur);
        historiqueCreance.setCreance(newCreance);
        historiqueCreance.setAction("MODIFICATION MONTANT AVANCE CREANCE");
        historiqueCreance.setCreatedDate(new Date());

        historiqueCreanceService.saveHistoriqueCreance(historiqueCreance);

        return new ResponseEntity<>(newCreance, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Creance>> getAllCreances() {
        List<Creance> creanceList = creanceService.findAllCreances();
        return new ResponseEntity<>(creanceList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Creance>> getAllCreancesOrderDesc() {
        List<Creance> creanceList = creanceService.findAllCreancesOrderDesc();
        return new ResponseEntity<>(creanceList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Creance>> getAllCreanceOf3LatestMonths() {
        List<Creance> creanceList = creanceService.findListCreanceOf3LatestMonth();
        return new ResponseEntity<>(creanceList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Creance>> getTop500OCreanceOrderByIdDesc() {
        List<Creance> creanceList = creanceService.findTop500OCreanceOrderByIdDesc();
        return new ResponseEntity<>(creanceList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Creance>> getAllCreanceByClientId(Long clientId) {
        List<Creance> creanceList = creanceService.findCreanceByClientId(clientId);
        return new ResponseEntity<>(creanceList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Creance>> getAllCreanceByClientIdAndStatus(Long clientId) {
        List<Creance> creanceList = creanceService.ListCreanceClientByClientIdAndStatus(clientId);
        return new ResponseEntity<>(creanceList, HttpStatus.OK);
    }

    @Override
    public int getNumberOfCreances() {
        return creanceService.getNumberOfCreances();
    }

    @Override
    public BigDecimal getSumTotalOfCreances() {
        return creanceService.countNumbersOfCreances();
    }

    @Override
    public BigDecimal getSumTotalOfCreanceInYear() {
        return creanceService.sumTotalOfCreanceInYear();
    }

    @Override
    public List<?> sumTotalOfCreancesPeerMonth() {
        return creanceService.sumTotalOfCreancesPeerMonth();
    }

    @Override
    public ResponseEntity<?> deleteCreance(Long id) {
        creanceService.deleteCreance(id);
        return ResponseEntity.ok().build();
    }
}
