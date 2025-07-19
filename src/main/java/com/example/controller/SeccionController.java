package com.example.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.Seccion;
import com.example.services.SeccionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/seccion", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class SeccionController {

    private final SeccionService seccionService;

    @GetMapping("listar")
    public List<Seccion> listarSecciones() {
        return seccionService.listar();
    }

    @PostMapping("insertar")
    public Seccion insertarSeccion(@Valid @RequestBody Seccion seccion) {
        return seccionService.insertar(seccion);
    }

    @PutMapping("actualizar/{id}")
    public Seccion actualizarSeccion(@PathVariable Integer id,@Valid @RequestBody Seccion seccion) {
        seccion.setIdSeccion(id);
        return seccionService.actualizar(id, seccion);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarSeccion(@PathVariable Integer id) {
        seccionService.eliminar(id);
        return ResponseEntity.noContent().build(); // HTTP 204, sin cuerpo
    }

}
