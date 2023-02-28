package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.CommandeApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Commande;
import com.wokite.net.backendstore.models.HistoriqueCommande;
import com.wokite.net.backendstore.models.LigneCommande;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.security.service.UserPrinciple;
import com.wokite.net.backendstore.services.*;
import com.wokite.net.backendstore.utils.GeneratedNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CommandeController implements CommandeApi {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private LigneCommandeService ligneCommandeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private HistoriqueCommandeService historiqueCommandeService;

    @Override
    public ResponseEntity<Commande> createCommande(Commande commande) {
        Commande commandeResultat;
        HistoriqueCommande historiqueCommande = new HistoriqueCommande();
        Utilisateur utilisateur = new Utilisateur();
        commande.setNumeroCommande(GeneratedNumber.generateCodeCommand());
        commandeResultat = commandeService.saveCommande(commande);
/*
        historiqueCommande.setUtilisateur(utilisateur);
        historiqueCommande.setCommande(commandeResultat);
        historiqueCommande.setAction("AJOUT COMMANDE");
        historiqueCommande.setCreatedDate(new Date());

        historiqueCommandeService.saveHistoriqueCommande(historiqueCommande);
*/
        return new ResponseEntity<>(commandeResultat, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Commande> enregistrerCommande(Commande commande, Long id) {

        Commande commandedResultant;

        Utilisateur userInfo = utilisateurService.findUtilisateurById(id).get();
        commande.setUtilisateur(userInfo);
        commande.setNumeroCommande(GeneratedNumber.generateCodeCommand());

        commandedResultant = commandeService.createCommande(commande);

        HistoriqueCommande historiqueCommande = new HistoriqueCommande();

        historiqueCommande.setUtilisateur(userInfo);
        historiqueCommande.setCommande(commandedResultant);
        historiqueCommande.setAction("AJOUT COMMANDE");
        historiqueCommande.setCreatedDate(new Date());

        historiqueCommandeService.saveHistoriqueCommande(historiqueCommande);

        List<LigneCommande> lineCommandedList = commande.getLcomms();
        for (LigneCommande ligneCommande : lineCommandedList) {
            ligneCommande.setNumero(commande.getNumeroCommande());
            ligneCommandeService.saveLigneCommande(ligneCommande);
        }

        return new ResponseEntity<>(commandedResultant, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Commande> createCommande(Commande commande, Long id) {

        Commande commandeResultat;

        HistoriqueCommande historiqueCommande = new HistoriqueCommande();

        Utilisateur utilisateur = utilisateurService.findUtilisateurById(id).get();
        commande.setUtilisateur(utilisateur);
        commande.setNumeroCommande(GeneratedNumber.generateCodeCommand());

        commandeResultat = commandeService.saveCommande(commande);

        historiqueCommande.setUtilisateur(utilisateur);
        historiqueCommande.setCommande(commandeResultat);
        historiqueCommande.setAction("AJOUT COMMANDE");
        historiqueCommande.setCreatedDate(new Date());

        historiqueCommandeService.saveHistoriqueCommande(historiqueCommande);

        return new ResponseEntity<>(commandeResultat, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Commande> createCommandeClient(Commande commandeClient, Long id) {
        return null;
    }


    @Override
    public ResponseEntity<Commande> updateCommande(Long catId, Commande commande) {

        Commande commandeResultat;

        HistoriqueCommande historiqueCommande = new HistoriqueCommande();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();

        commande.setId(catId);

        commandeResultat = commandeService.saveCommande(commande);

        historiqueCommande.setUtilisateur(utilisateur);
        historiqueCommande.setCommande(commandeResultat);
        historiqueCommande.setAction("MODIFICATION COMMANDE");
        historiqueCommande.setCreatedDate(new Date());

        historiqueCommandeService.saveHistoriqueCommande(historiqueCommande);

        return new ResponseEntity<>(commandeResultat, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Commande> getCommandeById(Long id) {
        Commande commande = commandeService.findCommandeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande that id is" + id + "not found"));
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandeList = commandeService.findAllCommande();
        return new ResponseEntity<>(commandeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Commande>> getAllCommandesOrderDesc() {
        List<Commande> commandeList = commandeService.findAllCommandesOrderDesc();
        return new ResponseEntity<>(commandeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Commande>> getAllCommandesOf3LatestMonths() {
        List<Commande> commandeList = commandeService.findListCommandesOf3LatestMonth();
        return new ResponseEntity<>(commandeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Commande>> getTop500OCommandesClientOrderByIdDesc() {
        List<Commande> commandeList = commandeService.findTop500OCommandesOrderByIdDesc();
        return new ResponseEntity<>(commandeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Commande>> getAllCommandesByClientId(Long clientId) {
        List<Commande> commandeList = commandeService.findCommandesByClientId(clientId);
        return new ResponseEntity<>(commandeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Commande>> getAllCommandesWithDatet(Date dateCommande) {
        return null;
    }

    @Override
    public Long getNumbersOfCommandes() {
        return commandeService.countNumbersOfCommandes();
    }

    @Override
    public BigDecimal sumTotalOfCommandesInMonth() {
        return commandeService.sumTotalOfCommandesInMonth();
    }

    @Override
    public BigDecimal sumTotalOfCommandesInYear() {
        return commandeService.sumTotalOfCommandesInYear();
    }

    @Override
    public List<?> getNumberTotalOfCommandePeerMonth() {
        return commandeService.countNumberTotalOfCommandePeerMonth();
    }

    @Override
    public List<?> getSumTotalOfCommandePeerMonth() {
        return commandeService.sumTotalOfCommandePeerMonth();
    }

    @Override
    public ResponseEntity<?> deleteCommande(Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.ok().build();
    }
}
