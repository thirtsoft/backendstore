package com.wokite.net.backendstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wokite.net.backendstore.controllers.api.ContratApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Contrat;
import com.wokite.net.backendstore.services.ContratService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
public class ContratController implements ContratApi {

    private static final Logger logger = LoggerFactory.getLogger(ContratController.class);

    private final Path rootLocation = Paths.get("C:\\Users\\Folio9470m\\AlAmine\\Contrat\\");

    private final String EXTERNAL_FILE_PATH = "C:/Users/Folio9470m/AlAmine/Contrat/";
    private final String contratsDir = "C://Users//Folio9470m//AlAmine//Contrat//";
    @Autowired
    ServletContext context;
    @Autowired
    private ContratService contratService;


    @Override
    public ResponseEntity<Contrat> createContrat(Contrat contrat) {
        Contrat contratResult = contratService.saveContrat(contrat);
        return new ResponseEntity<>(contratResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> createContrat(String cont, MultipartFile file) throws IOException {
        Contrat contrat = new ObjectMapper().readValue(cont, Contrat.class);
        if (file != null && !file.isEmpty()) {
            contrat.setFileContrat(file.getOriginalFilename());
            file.transferTo(new File(contratsDir + file.getOriginalFilename()));
        }

        contratService.saveContrat(contrat);

        return new ResponseEntity<>(contrat, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> addContratInPath(String cont, MultipartFile file) throws IOException {
        Contrat contrat = new ObjectMapper().readValue(cont, Contrat.class);
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Contrats/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            String numCont = "REF_" + Math.random() * 10;
            contrat.setReference(numCont);
            contrat.setFileContrat(filename);

            contratService.saveContrat(contrat);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Contrat is created");
    }

    @Override
    public ResponseEntity<Contrat> getContratById(Long id) {
        Contrat contrat = contratService.findContratById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande that id is" + id + "not found"));
        return new ResponseEntity<>(contrat, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Contrat> updateContrat(Long catId, Contrat contrat) {
        contrat.setId(catId);
        Contrat contratResult = contratService.saveContrat(contrat);
        return new ResponseEntity<>(contratResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Contrat>> getAllContrats() {
        List<Contrat> contratList = contratService.findAllContrats();
        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Contrat>> getAllContratsOrderDesc() {
        List<Contrat> contratList = contratService.findAllContratsOrderDesc();
        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Contrat>> getContratsByClientId(Long clientId) {
        List<Contrat> contratList = contratService.findContratByClientId(clientId);
        return new ResponseEntity<>(contratList, HttpStatus.OK);
    }

    @Override
    public void uploadContratFile(MultipartFile file, Long id) throws IOException {
        Contrat contrat = contratService.findContratById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat not found"));
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Contrats/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            contrat.setFileContrat(filename);

            contratService.saveContrat(contrat);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void downloadContratFile(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        File file = new File(context.getRealPath("/Contrats/" + File.separator + fileName));
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }

    }

    @Override
    public ResponseEntity<?> deleteContrat(Long id) {
        contratService.deleteContrat(id);
        return ResponseEntity.ok().build();
    }
}
