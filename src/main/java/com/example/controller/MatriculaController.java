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

import com.example.entidad.Matricula;
import com.example.services.MatriculaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/matricula", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;


    @GetMapping("listar")
    public List <Matricula> listarMatricula() {
        return matriculaService.listar();
    }

    @PostMapping("insertar")
    public Matricula insertarMatricula(@RequestBody Matricula matricula) {                
        return matriculaService.insertar(matricula);
    }

    @PutMapping("actualizar/{id}")
    public Matricula actualizarMatricula(@PathVariable Integer id, @RequestBody Matricula matricula) {
        matricula.setId_matricula(id);
        return matriculaService.actualizar(id, matricula);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity <String> eliminarMatricula(@PathVariable Integer id){
        matriculaService.eliminar(id);
        return ResponseEntity.ok("Matricula eliminado correctamente con Id: "+id);
    }




}
