package com.example.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.EstudianteLoginRequest;
import com.example.entidad.EstudianteLoginResponse;
import com.example.entidad.Seccion;
import com.example.services.EstudianteLoginService;
import com.example.services.SeccionService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "api/EstudianteLogin", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EstudianteLoginController {

    private final EstudianteLoginService loginService;

    //Cuando inicia sesion el estudiante
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EstudianteLoginRequest req, HttpSession session) {
        try {
            EstudianteLoginResponse resp = loginService.loginYObtenerCursos(req.codigo(), req.password(), session);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    //Para mostrar todas las secciones de ese curso
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Seccion>> getSeccionesPorCurso(@PathVariable Integer idCurso) {
        List<Seccion> secciones = loginService.listarPorCurso(idCurso);
        return ResponseEntity.ok(secciones);
    }

}
