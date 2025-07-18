package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.EstudianteLoginRequest;
import com.example.entidad.EstudianteLoginResponse;
import com.example.services.EstudianteLoginService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "api/EstudianteLogin", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EstudianteLoginController {

    private final EstudianteLoginService loginService;

      @PostMapping("/login")
     public ResponseEntity<?> login(@RequestBody EstudianteLoginRequest req, HttpSession session) {
        try {
            EstudianteLoginResponse resp = loginService.loginYObtenerCursos(req.codigo(), req.password(), session);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
