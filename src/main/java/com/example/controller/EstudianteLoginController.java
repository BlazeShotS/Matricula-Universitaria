package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.services.EstudianteLoginService;
import com.example.util.AuthenticationEstudianteRequest;

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
    public ResponseEntity<?> login(@RequestBody AuthenticationEstudianteRequest req, HttpSession session) {
        try {
            // Llamar al service (que devolverá Map o un record de respuesta)
            Map<String, Object> result = loginService.loginYObtenerCursos(
                    req.codigo(),
                    req.password(),
                    session
            );
            return ResponseEntity.ok(result);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
