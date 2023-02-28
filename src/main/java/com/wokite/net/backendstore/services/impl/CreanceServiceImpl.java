package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Creance;
import com.wokite.net.backendstore.models.LigneCreance;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.repository.CreanceRepository;
import com.wokite.net.backendstore.services.CreanceService;
import com.wokite.net.backendstore.services.LigneCreanceService;
import com.wokite.net.backendstore.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CreanceServiceImpl implements CreanceService {

    Logger logger = LoggerFactory.getLogger(CreanceServiceImpl.class);

    @Autowired
    private CreanceRepository creanceRepository;


    @Autowired
    private LigneCreanceService ligneCreanceService;

    @Autowired
    private ProductService productService;

    @Override
    public Creance saveCreance(Creance creance) {
        logger.info("Creance {}", creance);

        List<LigneCreance> ligneCreanceList = creance.getLcreances();

        if (ligneCreanceList == null || ligneCreanceList.size() == 0) {
            throw new IllegalArgumentException("Vous devez au moins selectionner un article");
        }

        if ((creance.getClient().getId() == null)) {
            throw new IllegalArgumentException("Vous devez selectionner un client");
        }

        for (LigneCreance ligneCreance : ligneCreanceList) {
            Product productInitial = productService.findProductById(ligneCreance.getProduct().getId()).get();
            if (ligneCreance.getQuantite() > productInitial.getQtestock()) {
                throw new IllegalArgumentException("La Quantity de stock du article est insuffusante");
            }
        }

        creanceRepository.save(creance);

        List<LigneCreance> ligneCreanceListResult = creance.getLcreances();

        double total = 0;
        double total1 = 0;

        for (LigneCreance ligneCreance : ligneCreanceListResult) {
            ligneCreance.setCreance(creance);
            ligneCreance.setNumeroLigneCreance(creance.getReference());

            ligneCreanceService.saveLigneCreance(ligneCreance);

            Product product = productService.findProductById(ligneCreance.getProduct().getId()).get();
            if (product != null) {
                product.setQtestock(product.getQtestock() - ligneCreance.getQuantite());
                productService.updateProduct(product.getId(), product);
            }

            ligneCreance.setPrix(product.getPrixVente());

            total += (ligneCreance.getQuantite() * product.getPrixVente());
        }

        total1 = total + creance.getSoldeCreance();

        creance.setTotalCreance(total1);
        creance.setStatus("ENCOURS");
        creance.setDateCreance(new Date());

        return creanceRepository.save(creance);

    }

    @Override
    public Creance updateCreance(Long creanceId, Creance creance) {
        if (!creanceRepository.existsById(creanceId)) {
            throw new ResourceNotFoundException("Creance that id is" + creanceId + "not found");
        }

        Optional<Creance> optionalCreance = creanceRepository.findById(creanceId);

        if (!optionalCreance.isPresent()) {
            throw new ResourceNotFoundException("Creance that id is" + creanceId + "not found");
        }

        Creance creanceResult = optionalCreance.get();

        creanceResult.setReference(creance.getReference());
        creanceResult.setLibelle(creance.getLibelle());
        creanceResult.setClient(creance.getClient());
        creanceResult.setSoldeCreance(creance.getSoldeCreance());
        creanceResult.setNbreJours(creance.getNbreJours());

        creanceResult.setTotalCreance(creance.getTotalCreance());
        creanceResult.setStatus(creance.getStatus());

        return creanceRepository.save(creanceResult);
    }

    @Override
    public void updateCreanceStatus(Long id, String status) {
        Creance creance = this.creanceRepository.findById(id).get();
        creance.setStatus(creance.getStatus());

        creanceRepository.updateCreanceStatus(status, id);
    }

    @Override
    public boolean updateStatus(Long reference, String status) {
        /*
        Optional<Creance> optionalCreance = this.creanceRepository.findByReference(reference);
        Creance creanceResult;
        if (optionalCreance.isPresent()) {
            creanceResult = optionalCreance.get();
            creanceResult.setStatus(status);
            this.creanceRepository.save(creanceResult);
            return true;
        }

         */
        return false;
    }

    @Override
    public boolean updateStatusCreance(String codeCreance, String status) {
        /*
        Optional<Creance> optionalCreance = this.creanceRepository.findByCodeCreance(codeCreance);
        Creance creanceResult;
        if (optionalCreance.isPresent()) {
            creanceResult = optionalCreance.get();
            creanceResult.setStatus(status);
            this.creanceRepository.save(creanceResult);
            return true;
        }

         */

        return false;
    }

    @Override
    public Creance setCreanceOnlyAvanceCreance(double avanceCreance, String id) {
        Optional<Creance> originalCreance = creanceRepository.findById(Long.valueOf(id));
        Creance creance = originalCreance.get();
        creance.setAvanceCreance(avanceCreance);
        return creanceRepository.save(creance);
    }

    @Override
    public Creance setCreanceOnlyStatus(String status, String id) {
        Optional<Creance> originalCreance = creanceRepository.findById(Long.valueOf(id));
        Creance creance = originalCreance.get();
        creance.setStatus(status);
        return creanceRepository.save(creance);
    }

    @Override
    public Optional<Creance> findCreanceById(Long creanceId) {
        if (!creanceRepository.existsById(creanceId)) {
            throw new ResourceNotFoundException("Creance that id is" + creanceId + "not found");
        }
        return creanceRepository.findById(creanceId);
    }

    @Override
    public List<Creance> findAllCreances() {
        return creanceRepository.findAll();
    }

    @Override
    public List<Creance> findAllCreancesOrderDesc() {
        return creanceRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Creance> findListCreanceOf3LatestMonth() {
        return creanceRepository.findListCreanceOf3LatestMonth();
    }

    @Override
    public List<Creance> findTop500OCreanceOrderByIdDesc() {
        return creanceRepository.findTop500ByOrderByIdDesc();
    }

    @Override
    public List<Creance> findCreanceByClientId(Long clientId) {
        return creanceRepository.ListCreanceClientByClientId(clientId);
    }

    @Override
    public List<Creance> ListCreanceClientByClientIdAndStatus(Long clientId) {
        return creanceRepository.ListCreanceClientByClientIdAndStatus(clientId);
    }

    @Override
    public int getNumberOfCreances() {
        return creanceRepository.countNumberOfCreance();
    }

    @Override
    public BigDecimal countNumbersOfCreances() {
        return creanceRepository.countNumbersOfCreances();
    }

    @Override
    public BigDecimal sumTotalOfCreanceInYear() {
        return creanceRepository.sumTotalOfCreanceByYear();
    }

    @Override
    public List<?> sumTotalOfCreancesPeerMonth() {
        return creanceRepository.sumTotalOfCreancesByMonth();
    }

    @Override
    public void deleteCreance(Long id) {

    }
}
