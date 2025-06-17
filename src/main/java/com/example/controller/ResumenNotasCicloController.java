package com.example.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.ResumenNotasCiclo;
import com.example.services.ResumenNotasCicloService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping (value = "api/resumen", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ResumenNotasCicloController {

    private final ResumenNotasCicloService resumenNotasCicloService;


    @GetMapping("listar")
    public List <ResumenNotasCiclo> listarResumen() {
        return resumenNotasCicloService.listar();
    }
    
    @PostMapping("insertar")
    public ResumenNotasCiclo insertarResumen(@Valid @RequestBody ResumenNotasCiclo resumenNotasCiclo) {        
        return resumenNotasCicloService.insertar(resumenNotasCiclo);
    }

    @PutMapping("actualizar/{id}")
    public ResumenNotasCiclo actualizarResumen(@PathVariable Integer id,@Valid @RequestBody ResumenNotasCiclo resumenNotasCiclo) {
        resumenNotasCiclo.setId(id);
        return resumenNotasCicloService.actualizar(id, resumenNotasCiclo);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity <String> eliminarResumen(@PathVariable Integer id){
        resumenNotasCicloService.eliminar(id);
        return ResponseEntity.ok("Resumen de notas del ciclo eliminado correctamente con id :"+id);
    }

}
