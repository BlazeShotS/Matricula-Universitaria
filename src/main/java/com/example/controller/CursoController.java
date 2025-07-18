package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import com.example.entidad.Curso_InfoCurso;
import com.example.entidad.Curso_InfoCursoResponse;
import com.example.services.CursoServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/curso", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CursoController {

    private final CursoServices cursoServices;

    @GetMapping()
    public List<Curso> listarCursos() {
        return cursoServices.listar();
    }

    @PostMapping()
    public Curso insertarCurso(@Valid @RequestBody Curso curso) {
        return cursoServices.insertar(curso);
    }

    @PutMapping()
    public Curso actualizarCurso(@PathVariable Integer id, @Valid @RequestBody Curso curso) {
        curso.setId_curso(id);
        return cursoServices.actualizar(id, curso);
    }

    @DeleteMapping()
    public ResponseEntity<String> eliminarCurso(@PathVariable Integer id) {
        cursoServices.eliminar(id);
        return ResponseEntity.ok("Carrera eliminado correctamente con Id: " + id);
    }

    /*--------------------------PARA TRANSACTION-----------------------------*/

    //Para el get uso el record Curso_InfoCursoResponse
    @GetMapping("/listar")
    public ResponseEntity<List<Curso_InfoCursoResponse>> listarCursoInfoCurso() {
        List<Curso_InfoCursoResponse> lista = cursoServices.listarCursosConInfo();
        return ResponseEntity.ok(lista);
    }

    // Post para guardar tanto el curso con info curso (TRANSACTION), uso el record Curso_InfoCurso
    @PostMapping(value = "/completo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso_InfoCursoResponse> insertCursoInfoCurso(@Valid @RequestBody Curso_InfoCurso curso_InfoCurso) {
        try {
            Curso_InfoCursoResponse respuesta = cursoServices.crearCursoInfoCurso(curso_InfoCurso);//LO QUE ME DEVOLVIO LO VINCULO O PONGO EN Curso_InfoCursoResponse , ya que el GET llama a eso y en angular llama al get donde estan los ids
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<?> actualizarCursoInfoCurso(@PathVariable Integer id,@Valid @RequestBody Curso_InfoCurso request) {

        try {
            cursoServices.actualizarCursoConInfo(id, request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarCursoInfoCurso(@PathVariable Integer id) {
        try {
            cursoServices.eliminarCursoConInfo(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar: " + e.getMessage());
        }
    }

    /* PARA FILTRAR POR CARRERA */
    @GetMapping("/filtrar/{idCarrera}")
    public ResponseEntity<List<Curso_InfoCursoResponse>> obtenerPorCarrera(@PathVariable Integer idCarrera) {
        return ResponseEntity.ok(cursoServices.buscarPorCarrera(idCarrera));
    }

}
