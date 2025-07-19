package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.entidad.EstudianteLoginRequest;
import com.example.entidad.EstudianteLoginResponse;
import com.example.entidad.Seccion;
import com.example.entidad.procesar.matricula.MatriculaRequest;
import com.example.entidad.procesar.matricula.MatriculaResponse;
import com.example.repositories.SeccionRepository;
import com.example.services.EstudianteLoginService;
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
    private final SeccionRepository seccionRepository;

    // Cuando inicia sesion el estudiante
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EstudianteLoginRequest req) {
        try {
            EstudianteLoginResponse resp = loginService.loginYObtenerCursos(req.codigo(), req.password());
            return ResponseEntity.ok(resp);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // Para mostrar todas las secciones de ese curso
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Seccion>> getSeccionesPorCurso(@PathVariable Integer idCurso) {
        List<Seccion> secciones = loginService.listarPorCurso(idCurso);
        return ResponseEntity.ok(secciones);
    }

    // PROCESAR MATRICULA
    @PostMapping("/procesar")
    public ResponseEntity<?> procesar(@RequestBody MatriculaRequest request) {
        try {
            List<MatriculaResponse> resultado = loginService.procesarMatricula(request);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            // Por ejemplo cupos agotados
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno: " + e.getMessage()));
        }
    }

    //PARA EL GET , ME LLEGAN LOS ID SECCION Y ID MATRICULAS , DEVUELVO TODO LOS ID SECCION
    @GetMapping("/secciones/by-ids")
    public ResponseEntity<List<Seccion>> getSeccionesByIds(@RequestParam("ids") List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        List<Seccion> secciones = seccionRepository.findAllById(ids);
        return ResponseEntity.ok(secciones);
    }

}
