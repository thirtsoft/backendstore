package com.wokite.net.backendstore.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Contrat;
import com.wokite.net.backendstore.repository.ContratRepository;
import com.wokite.net.backendstore.services.ContratService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContratServiceImpl implements ContratService {

    private final Path fileStorageLocation = Paths.get("C:\\Folio9470m\\AlAmine\\Contrat");

    private final ContratRepository contratRepository;

    public ContratServiceImpl(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    public Contrat saveContrat(Contrat contrat) {
        contrat.setActif(true);
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat updateContrat(Long id, Contrat contrat) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat N° " + id + "not found");
        }
        Optional<Contrat> optionalContrat = contratRepository.findById(id);
        if (!optionalContrat.isPresent()) {
            throw new ResourceNotFoundException("Contrat not found");
        }
        Contrat contratResult = optionalContrat.get();
        contratResult.setReference(contrat.getReference());
        contratResult.setNature(contrat.getNature());
        contratResult.setClient(contrat.getClient());
        contratResult.setDescription(contrat.getDescription());
        contratResult.setDateDebutContrat(contrat.getDateDebutContrat());
        contratResult.setDateFinContrat(contrat.getDateFinContrat());

        return contratRepository.save(contratResult);
    }

    @Override
    public Optional<Contrat> findContratById(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat N° " + id + "not found");
        }
        return contratRepository.findById(id);
    }

    @Override
    public List<Contrat> findAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public List<Contrat> findAllContratsOrderDesc() {
        return contratRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Contrat> findContratByClientId(Long clientId) {
        return contratRepository.findLitContratByClientId(clientId);
    }

    @Override
    public Contrat createContrat(String contrat, MultipartFile fileContrant) throws IOException {
        Contrat contratResult = new ObjectMapper().readValue(contrat, Contrat.class);
        contratResult.setFileContrat(fileContrant.getOriginalFilename());
        contratResult.setActif(true);
        return contratRepository.save(contratResult);
    }

    @Override
    public Resource loadFileAsResource(String fileName) throws Exception {
       return null;
    }

    @Override
    public void deleteContrat(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat not found");
        }
        Contrat contrat = contratRepository.findById(id).get();
        contrat.setActif(false);
        contratRepository.save(contrat);
    }
}
