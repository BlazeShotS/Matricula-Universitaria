package com.example.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.Estudiante_Universidad;
import com.example.services.Estudiante_UniversidadService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value = "api/EstudianteUniversidad", produces = MediaType.APPLICATION_JSON_VALUE)
public class Estudiante_UniversidadController {

    //inyeccion de dependencia por contructor
    private final Estudiante_UniversidadService service;
    public Estudiante_UniversidadController (Estudiante_UniversidadService service){
        this.service = service;
    }


    @GetMapping("lista")
    public List <Estudiante_Universidad> selectEstudianteUniversidad () {
        return service.listarTodas();
    }
    
 

}
