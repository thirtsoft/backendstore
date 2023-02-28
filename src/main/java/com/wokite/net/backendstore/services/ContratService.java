package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Contrat;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ContratService {

    Contrat saveContrat(Contrat contrat);

    Contrat updateContrat(Long id, Contrat contrat);

    Optional<Contrat> findContratById(Long id);

    List<Contrat> findAllContrats();

    List<Contrat> findAllContratsOrderDesc();
    
    List<Contrat> findContratByClientId(Long clientId);

    Contrat createContrat(String contrat, MultipartFile fileContrant) throws IOException;

    Resource loadFileAsResource(String fileName) throws Exception;

    void deleteContrat(Long id);


}
