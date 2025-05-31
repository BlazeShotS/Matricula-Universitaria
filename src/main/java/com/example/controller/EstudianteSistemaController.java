package com.example.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.EstudianteSistema;
import com.example.services.EstudianteSistemaService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(value = "api/EstudianteSistema" , produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EstudianteSistemaController {

    private EstudianteSistemaService estudianteSistemaService;


    @GetMapping("lista")
    public List <EstudianteSistema> selectEstudianteSistema () {
        return estudianteSistemaService.listarTodas();
    }
    
    @PostMapping("insertar")
    public EstudianteSistema insertarEstudianteSistema(@RequestBody EstudianteSistema estudianteSistema) {
        return estudianteSistemaService.insertEstudianteSistema(estudianteSistema);
    }


    @PutMapping("actualizar/{id}")
    public EstudianteSistema updateEstudianteSistema (@PathVariable Integer id, @RequestBody EstudianteSistema estudianteSistema) {
        estudianteSistema.setCodigo_estudiante(id);
        return estudianteSistemaService.updateEstudianteSistema(id, estudianteSistema);
    }
    


}
