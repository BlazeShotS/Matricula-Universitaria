package com.example.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.Carrera;
import com.example.services.CarreraService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "api/carrera" , produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CarreraController {


    private final CarreraService carreraService;

    @GetMapping("listar")
    public List <Carrera> listarCarreras() {
        return carreraService.listar();
    }

    @PostMapping("insertar")
    public Carrera insertarCarrera(@Valid @RequestBody Carrera carrera) {                
        return carreraService.insertar(carrera);
    }

    @GetMapping("lista/{idCarrera}")
    public Carrera selectCarreraId(@PathVariable Integer idCarrera) {
        return carreraService.obtenerPorId(idCarrera);
    }

    @PutMapping("actualizar/{id}")
    public Carrera actualizarCarrera(@PathVariable Integer id,@Valid @RequestBody Carrera carrera) {
        carrera.setId_carrera(id);
        return carreraService.actualizar(id, carrera);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity <String> eliminarCarrera(@PathVariable Integer id){
        carreraService.eliminar(id);
        return ResponseEntity.ok("Carrera eliminado correctamente con Id: "+id);
    }


}
