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

import com.example.entidad.Profesores;
import com.example.services.ProfesoresService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/profesores", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProfesoresController {

    private final ProfesoresService profesoresService;
    
    @GetMapping("listar")
    public List <Profesores> listarProfesores() {
        return profesoresService.listar();
    }

    @PostMapping("insertar")
    public Profesores insertarProfesor(@Valid @RequestBody Profesores profesores) {     
        return profesoresService.insertar(profesores);
    }

    @GetMapping("lista/{idProfesor}")
    public Profesores selectProfesorId(@PathVariable Integer idProfesor) {
        return profesoresService.obtenerPorId(idProfesor);
    }

    @PutMapping("actualizar/{id}")
    public Profesores actualizarProfesor(@PathVariable Integer id,@Valid @RequestBody Profesores profesores) {
        profesores.setId_profesor(id);
        return profesoresService.actualizar(id, profesores);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity <String> eliminarProfesor(@PathVariable Integer id){
        profesoresService.eliminar(id);
        return ResponseEntity.ok("Profesor eliminado correctamente con Id: "+id);
    }



}
