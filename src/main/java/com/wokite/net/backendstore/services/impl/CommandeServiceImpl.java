package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Commande;
import com.wokite.net.backendstore.models.LigneCommande;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.repository.CommandeRepository;
import com.wokite.net.backendstore.repository.LigneCommandeRepository;
import com.wokite.net.backendstore.services.CommandeService;
import com.wokite.net.backendstore.services.LigneCommandeService;
import com.wokite.net.backendstore.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CommandeServiceImpl implements CommandeService {

    @Autowired private CommandeRepository commandeRepository;

    @Autowired private LigneCommandeService ligneCommandeService;

    @Autowired private ProductService productService;

    @Override
    public Commande saveCommande(Commande commande) {
        System.out.println("Initial Numero Commande " + commande.getNumeroCommande());
        List<LigneCommande> ligneCommandeList = commande.getLcomms();
        if (ligneCommandeList == null || ligneCommandeList.size() == 0) {
            throw new IllegalArgumentException("Vous devez au moins commander un produit");
        }
        if ((commande.getClient().getId() == null)) {
            throw new IllegalArgumentException("Vous devez selectionner un client");
        }

        for (LigneCommande ligneCommande : ligneCommandeList) {
            Product produitInitial = productService.findProductById(ligneCommande.getProduct().getId()).get();
            if (ligneCommande.getQuantite() > produitInitial.getQtestock()) {
                throw new IllegalArgumentException("La Quantité de stock du produit est insuffusante");
            }
        }
        commandeRepository.save(commande);
        System.out.println("1er Numero Commande " + commande.getNumeroCommande());

        List<LigneCommande> ligneCommandeList1 = commande.getLcomms();
        double total = 0;
        for (LigneCommande ligneCommande : ligneCommandeList1) {
            ligneCommande.setCommande(commande);
            ligneCommande.setNumero(commande.getNumeroCommande());
            ligneCommandeService.saveLigneCommande(ligneCommande);

            Product product = productService.findProductById(ligneCommande.getProduct().getId()).get();
            if (product != null) {
                product.setQtestock(product.getQtestock() - ligneCommande.getQuantite());
                productService.updateProduct(product.getId(), product);
            }
            ligneCommande.setPrix(product.getPrixVente());

            total += (ligneCommande.getQuantite() * ligneCommande.getPrixCommande());

        }

        commande.setTotalCommande(total);
        commande.setStatus("VALIDEE");
        commande.setDateCommande(new Date());

        System.out.println("Dernier Numero Commande " + commande.getNumeroCommande());

        return commandeRepository.save(commande);


    }

    @Override
    public Commande createCommande(Commande commande) {
        List<LigneCommande> ligneCommandeList = commande.getLcomms();
        ligneCommandeService.saveListLigneCmd(ligneCommandeList);
        double total = 0;
        for (int compteur = 0; compteur < ligneCommandeList.size(); compteur++) {
            LigneCommande ligneCommande = ligneCommandeList.get(compteur);
            Product product = productService.findProductById(ligneCommande.getProduct().getId()).get();
            ligneCommande.setProduct(product);
            ligneCommandeService.saveLigneCommande(ligneCommande);

            total += ligneCommande.getQuantite() * ligneCommande.getProduct().getPrixVente();
        }

        commande.setTotalCommande(total);
        commande.setLcomms(ligneCommandeList);
        return commandeRepository.save(commande);
    }

    @Override
    public ResponseEntity<String> createOrder(Commande commande) {
        try {
            Commande nouvelleCommande = new Commande();
            List<LigneCommande> ligneCommandeList;
            ligneCommandeList = commande.getLcomms();

            for (int compteur = 0; compteur < ligneCommandeList.size(); compteur++) {
                LigneCommande ligneCommande = ligneCommandeList.get(compteur);
                Product product = ligneCommande.getProduct();
                if (ligneCommande.getQuantite() <= product.getQtestock()) {
                    Product nouveauProduit = productService.findProductById(product.getId()).get();
                    nouveauProduit.setQtestock(product.getQtestock() - ligneCommande.getQuantite());
                    productService.saveProduct(nouveauProduit);

                } else return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("This order cannot be created[Empty inventory]!(" + HttpStatus.BAD_REQUEST + ")");
            }

            nouvelleCommande.setNumeroCommande(commande.getNumeroCommande());
            nouvelleCommande.setClient(commande.getClient());
            nouvelleCommande.setTotalCommande(commande.getTotalCommande());
            nouvelleCommande.setStatus(commande.getStatus());
            nouvelleCommande.setDateCommande(new Date());

            nouvelleCommande.setLcomms(ligneCommandeService.saveListLigneCmd(ligneCommandeList));

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Order has been created!(" + HttpStatus.CREATED + ")");

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "This order cannot be created!", e);
        }

    }

    @Override
    public Commande updateCommande(Long comId, Commande commande) {
        if (!commandeRepository.existsById(comId)) {
            throw new ResourceNotFoundException("Commande that id is" + comId + "not found");
        }
        Optional<Commande> optionalCommande = commandeRepository.findById(comId);
        if (!optionalCommande.isPresent()) {
            throw new ResourceNotFoundException("Commande not found");
        }

        Commande commandeResult = optionalCommande.get();

        commandeResult.setNumeroCommande(commande.getNumeroCommande());
        commandeResult.setDateCommande(commande.getDateCommande());
        commandeResult.setClient(commande.getClient());
        commandeResult.setTotalCommande(commande.getTotalCommande());
        commandeResult.setStatus(commande.getStatus());

        return commandeRepository.save(commandeResult);
    }

    @Override
    public Optional<Commande> findCommandeById(Long comId) {
        if (!commandeRepository.existsById(comId)) {
            throw new ResourceNotFoundException("CommandeClient not found");
        }
        return commandeRepository.findById(comId);
    }

    @Override
    public Commande findByNumeroCommande(Long numeroCommande) {
        return commandeRepository.findByNumeroCommande(numeroCommande);
    }

    @Override
    public int getNombreCommandes(Date d1, Date d2) {
        return 0;
    }

    @Override
    public int getNumberOfCommande() {
        return 0;
    }

    @Override
    public Long countNumbersOfCommandes() {
        return commandeRepository.count();
    }

    @Override
    public BigDecimal sumTotalOfCommandesInMonth() {
        return commandeRepository.sumTotalOfCommandesByMonth();
    }

    @Override
    public BigDecimal sumTotalOfCommandesInYear() {
        return commandeRepository.sumTotalOfCommandesByYear();
    }

    @Override
    public List<Commande> findAllCommande() {
        return commandeRepository.findAll();
    }

    @Override
    public List<Commande> findAllCommandesOrderDesc() {
        return null;
    }

    @Override
    public List<Commande> findListCommandesOf3LatestMonth() {
        return commandeRepository.findListCommandeOf3LatestMonth();
    }

    @Override
    public List<Commande> findTop500OCommandesOrderByIdDesc() {
        return commandeRepository.findTop500ByOrderByIdDesc();
    }

    @Override
    public List<Commande> findCommandesByClientId(Long clientId) {
        return commandeRepository.ListCommandeByClientId(clientId);
    }

    @Override
    public List<Commande> findCommandesByDate(Date dateCommande) {
        return commandeRepository.findAllByDateCommande(dateCommande);
    }

    @Override
    public List<?> countNumberTotalOfCommandePeerMonth() {
        return commandeRepository.countNumberOfCommandeByMonth();
    }

    @Override
    public List<?> sumTotalOfCommandePeerMonth() {
        return commandeRepository.sumTotalOfCommandeByMonth();
    }

    @Override
    public ResponseEntity<Object> deleteCommandeClient(Long id) {
        if (!commandeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Commande not found");
        }
        commandeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteCommande(Long id) {
        Optional<Commande> commandeInfo = commandeRepository.findById(id);
        if (commandeInfo.isPresent()) {
            Commande commandeClient = commandeInfo.get();
            ligneCommandeService.deleteLcomByNumero(commandeClient.getNumeroCommande());
            commandeRepository.delete(commandeClient);
        }
    }
}
