package com.example.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.EstudianteUniversidad;
import com.example.services.EstudianteUniversidadService;

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

    //Para insertar
    @PostMapping("insertar")
    public EstudianteUniversidad insertEstudianteUniversidad (@RequestBody EstudianteUniversidad estudiante_Universidad){
        return service.insert(estudiante_Universidad);
    }

    //El dni de estudiante seria mi id
    //Para buscar por id
    @GetMapping("lista/{dni}")
     public EstudianteUniversidad selectEstudianteUniversidadId (@PathVariable Integer idestudianteUniversidad){
        return service.ObtenerPorId(idestudianteUniversidad);
    }
    
    //Para actualizar
    @PutMapping("actualizar/{dni}")
    public EstudianteUniversidad updateEstudianteUniversidad (@PathVariable Integer idestudianteUniversidad, @RequestBody EstudianteUniversidad estudiante_Universidad) {
        estudiante_Universidad.setDni_estudiante(idestudianteUniversidad);
        return service.ActualizarEstudianteUniversidad(idestudianteUniversidad, estudiante_Universidad);
    }

    //Para eliminar
    @DeleteMapping("eliminar/{dni}")
    public ResponseEntity <String> deleteEstudianteUniversidad (@PathVariable Integer idestudianteUniversidad){
        service.eliminar(idestudianteUniversidad);
        return ResponseEntity.ok("Estudiante de universidad eliminado con exito de id: "+idestudianteUniversidad);
    }

    

 

}
