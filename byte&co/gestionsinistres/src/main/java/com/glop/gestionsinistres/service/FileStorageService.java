package com.glop.gestionsinistres.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.upload.dir.constats}")
    private String uploadConstatDir;

    @Value("${app.upload.dir.declarationPolices}")
    private String uploadDeclarationPoliceDir;

    @Value("${app.upload.dir.dossierMedicaux}")
    private String uploadDossierMedicalDir;

    public String storeFile(MultipartFile file, String directory) throws IOException {
        String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path destination = Paths.get(directory).resolve(filename).normalize();
        Files.createDirectories(destination.getParent());
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println(">>> Fichier enregistré ici : " + destination.toAbsolutePath());
        return destination.toString();
    }

    public String storeConstat(MultipartFile file) throws IOException {
        return storeFile(file, uploadConstatDir);
    }

    public String storeDeclarationPolice(MultipartFile file) throws IOException {
        return storeFile(file, uploadDeclarationPoliceDir);
    }

    public String storeDossierMedical(MultipartFile file) throws IOException {
        return storeFile(file, uploadDossierMedicalDir);
    }
}
