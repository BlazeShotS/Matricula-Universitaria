package com.example.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.EstudianteUniversidad;
import com.example.services.EstudianteUniversidadService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "api/EstudianteUniversidad", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstudianteUniversidadController {

    //inyeccion de dependencia por contructor
    private final EstudianteUniversidadService service;
    public EstudianteUniversidadController (EstudianteUniversidadService service){
        this.service = service;
    }


    //Listar todos
    @GetMapping("lista")
    public List <EstudianteUniversidad> selectEstudianteUniversidad () {
        return service.listarTodas();
    }

    //El dni de estudiante seria mi id
    //Para buscar por id
    @GetMapping("lista/{dni}")
     public EstudianteUniversidad selectEstudianteUniversidadId (@PathVariable Integer dni){
        return service.ObtenerPorDni(dni);
    }

    //Para insertar
    @PostMapping("insertar")
    public EstudianteUniversidad insertarEstudianteUniversidad (@Valid @RequestBody EstudianteUniversidad estudiante_Universidad){
        return service.insertar(estudiante_Universidad);
    }
 
    //Para actualizar
    @PutMapping("actualizar/{dni}")  //La url dni tiene que ir lo mismo en mi @PathVariable dni
    public EstudianteUniversidad actualizarEstudianteUniversidad (@PathVariable Integer dni,@Valid @RequestBody EstudianteUniversidad estudiante_Universidad) {
        estudiante_Universidad.setDni_estudiante(dni);
        return service.Actualizar(dni, estudiante_Universidad);
    }

    //Para eliminar
    @DeleteMapping("eliminar/{dni}") //La url dni tiene que ir lo mismo en mi @PathVariable dni
    public ResponseEntity <String> eliminarEstudianteUniversidad (@PathVariable Integer dni){
        service.eliminar(dni);
        return ResponseEntity.ok("Estudiante que averiguo info de la universidad eliminado con exito de id: "+dni);
    }

    

 

}
