package com.example.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.entidad.Carrera;
import com.example.entidad.Curso;
import com.example.entidad.EstudianteSistema;
import com.example.entidad.ResumenNotasCiclo;
import com.example.repositories.CursoRepository;
import com.example.repositories.EstudianteSistemaRepository;
import com.example.repositories.ResumenNotasCicloRepository;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstudianteLoginService {

    private final EstudianteSistemaRepository estudianteSistemaRepository;
    private final ResumenNotasCicloRepository resumenNotasCicloRepository;
    private final CursoRepository cursoRepository;

    public Map<String, Object> loginYObtenerCursos(Integer codigo, String password, HttpSession session) {
        //Validar credenciales
        EstudianteSistema estudiante = estudianteSistemaRepository.login(codigo, password).orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        //Guardar código en sesión
        session.setAttribute("codigo_estudiante", estudiante.getCodigo_estudiante());

        //Obtener último resumen del estudiante (con periodo + carrera)
        var ultimoResumenOpt = resumenNotasCicloRepository.findTopByEstudianteSistemaOrderByPeriodoDesc(estudiante);

        if (ultimoResumenOpt.isEmpty()) {
            throw new RuntimeException("No se encontró historial de ciclos (ResumenNotasCiclo) para el estudiante. Registre un ciclo inicial primero.");
        }

        ResumenNotasCiclo ultimoResumen = ultimoResumenOpt.get();
        int ultimoPeriodo = ultimoResumen.getPeriodo() != null ? ultimoResumen.getPeriodo() : 0;

        // Carrera tomada del último ResumenNotasCiclo
        Carrera carrera = ultimoResumen.getCarrera();
        if (carrera == null) {
            throw new RuntimeException("El último ResumenNotasCiclo no tiene carrera asociada.");
        }
         
        int nuevoPeriodo = ultimoPeriodo + 1;

        //Traer cursos de esa carrera SOLO del nuevo ciclo
        List<Curso> cursos = cursoRepository.findByCarreraAndCiclo(carrera, nuevoPeriodo);

        // Armar respuesta simple para Angular
        Map<String, Object> resp = new HashMap<>();
        resp.put("codigo_estudiante", estudiante.getCodigo_estudiante());
        resp.put("carreraId", carrera.getId_carrera());
        resp.put("carreraNombre", carrera.getNombreCarrera());
        resp.put("periodoActual", ultimoPeriodo);
        resp.put("periodoMatricula", nuevoPeriodo);
        resp.put("cursos", cursos);

        return resp;
    }

}
