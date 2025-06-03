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

import com.example.entidad.Curso;
import com.example.services.CursoServices;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/curso" , produces = MediaType.APPLICATION_JSON_VALUE )
@AllArgsConstructor
public class CursoController {

    private final CursoServices cursoServices;


    
    @GetMapping("listar")
    public List <Curso> listarCursos() {
        return cursoServices.listar();
    }

    @PostMapping("insertar")
    public Curso insertarCurso(@RequestBody Curso curso) {                
        return cursoServices.insertar(curso);
    }

    @PutMapping("actualizar/{id}")
    public Curso actualizarCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        curso.setId_curso(id);
        return cursoServices.actualizar(id, curso);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity <String> eliminarCurso(@PathVariable Integer id){
        cursoServices.eliminar(id);
        return ResponseEntity.ok("Carrera eliminado correctamente con Id: "+id);
    }


    
}
