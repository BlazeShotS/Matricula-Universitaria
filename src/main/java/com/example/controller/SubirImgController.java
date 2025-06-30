package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/imagen")
public class SubirImgController {

    private final String UPLOAD_DIR = "uploads/img/";

    @PostMapping("/upload-img")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            // Generar nombre único
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);

            // Crear carpeta si no existe
            Files.createDirectories(path.getParent());

            // Guardar archivo
            Files.write(path, file.getBytes());

            // Retornar nombre para guardar en la entidad
            return ResponseEntity.ok(fileName);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir imagen");
        }
    }

}
