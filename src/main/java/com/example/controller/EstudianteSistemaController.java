package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.EstudianteSistema;
import com.example.services.EstudianteSistemaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "api/EstudianteSistema", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EstudianteSistemaController {

    private EstudianteSistemaService estudianteSistemaService;

    @GetMapping("lista")
    public List<EstudianteSistema> selectEstudianteSistema() {
        return estudianteSistemaService.listarTodas();
    }

    @PostMapping("insertar")
    public ResponseEntity<?> insertarEstudianteSistema(@Valid @RequestBody EstudianteSistema estudianteSistema) {       
        try {
            EstudianteSistema nuevo = estudianteSistemaService.insertar(estudianteSistema);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("actualizar/{id}")
    public EstudianteSistema actualizarEstudianteSistema(@PathVariable Integer id,
            @Valid @RequestBody EstudianteSistema estudianteSistema) {
        estudianteSistema.setCodigo_estudiante(id);
        return estudianteSistemaService.actualizar(id, estudianteSistema);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarEstudianteSistema(@PathVariable Integer id) {
        estudianteSistemaService.eliminar(id);
        return ResponseEntity.ok("Estudiante eliminado de la universidad correctamente: " + id);
    }

}
