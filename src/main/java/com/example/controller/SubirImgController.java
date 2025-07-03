package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/upload-img")                           //Esto espera
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            // Generar nombre único (Es decir lo que llega del frontend si llego carlog.jpg aca se genera un nombre unico) y se guardara algo asi uploads/img/f3c9a77a-e2c2-4fd5-826b-d7c2a4573f3f_mifoto.jpg
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);

            // Crear carpeta si no existe
            Files.createDirectories(path.getParent());

            // Guardar archivo
            //Convierte la imagen en bytes y la escribe en la ruta que definiste.
            Files.write(path, file.getBytes());

            // Si todo va bien , devuelve el nombre del archivo para que se pueda guardar en la entidad a una base de datos
            return ResponseEntity.ok(fileName);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir imagen");
        }
    }

}
