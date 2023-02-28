package com.wokite.net.backendstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wokite.net.backendstore.controllers.api.VersementApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Versement;
import com.wokite.net.backendstore.services.VersementService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class VersementController implements VersementApi {

    private final String EXTERNAL_FILE_PATH = "C:/Users/Folio9470m/AlAmine/Versement/";
    private final String versementsDir = "C://Users//Folio9470m//AlAmine//Versement//";

    @Autowired
    ServletContext context;
    @Autowired
    private VersementService versementService;


    @Override
    public ResponseEntity<Versement> saveVersement(Versement versement) {
        if (versement.getNumVersement() != null) {
            return new ResponseEntity<>(versement, HttpStatus.BAD_REQUEST);
        }
        String numVer = "VERS_" + Math.random() * 10;
        versement.setNumVersement(numVer);
        versement.setDateVersement(new Date());
        Versement versementResultant = versementService.saveVersement(versement);
        return new ResponseEntity<>(versementResultant, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> createVersement(String vers, MultipartFile file) throws IOException {
        Versement versement = new ObjectMapper().readValue(vers, Versement.class);
        if (file != null && !file.isEmpty()) {
            versement.setFileVersement(file.getOriginalFilename());
            file.transferTo(new File(versementsDir + file.getOriginalFilename()));
        }

        Versement versementResultant = versementService.saveVersement(versement);

        return new ResponseEntity<>(versementResultant, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> createVersementInPath(String vers, MultipartFile file) throws IOException {
        Versement versement = new ObjectMapper().readValue(vers, Versement.class);
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Versements/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            String numVer = "VERS_" + Math.random() * 10;
            versement.setNumVersement(numVer);
            versement.setFileVersement(filename);

            versementService.saveVersement(versement);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Versement is created");
    }

    @Override
    public ResponseEntity<Versement> updateVersement(Long id, Versement versement) {
        String numVer = "VERS_" + Math.random() * 10;
        versement.setId(id);
        versement.setNumVersement(numVer);
        Versement versementResultant = versementService.updateVersement(id, versement);
        return new ResponseEntity<>(versementResultant, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Versement> getVersementById(Long id) {
        Versement versementResultant = versementService.findVersementById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Versement " + id + "not found"));
        return new ResponseEntity<>(versementResultant, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Versement>> getAllVersements() {
        List<Versement> versementList = versementService.findAllVersements();
        return new ResponseEntity<>(versementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Versement>> getAllVersementOrderDesc() {
        List<Versement> versementList = versementService.findAllVersementsOrderDesc();
        return new ResponseEntity<>(versementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Versement>> getAllVersementsByEmployeId(Long empId) {
        List<Versement> versementList = versementService.findVersementByEmployeId(empId);
        return new ResponseEntity<>(versementList, HttpStatus.OK);
    }

    @Override
    public void uploadVersementFile(MultipartFile file, Long id) throws IOException {
        Versement versement = versementService.findVersementById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Versement not found"));
        versement.setFileVersement(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home") + "/AlAmine/Versement/" + versement.getFileVersement()), file.getBytes());

        versementService.saveVersement(versement);
    }

    @Override
    public void uploadVersementFileInPath(MultipartFile file, Long id) throws IOException {
        Versement versement = versementService.findVersementById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Versement not found"));
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Versements/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            versement.setFileVersement(filename);

            versementService.saveVersement(versement);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadVersementFile(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        File file = new File(context.getRealPath("/Versements/" + File.separator + fileName));
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
    public ResponseEntity<?> deleteVersement(Long id) {
        versementService.deleteVersement(id);
        return ResponseEntity.ok()
                .build();
    }
}
