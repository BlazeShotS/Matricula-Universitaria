package com.example.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.entidad.Carrera;
import com.example.entidad.Curso;
import com.example.entidad.CursoConInfoResponse;
import com.example.entidad.EstudianteLoginResponse;
import com.example.entidad.EstudianteSistema;
import com.example.entidad.InfoCurso;
import com.example.entidad.ResumenNotasCiclo;
import com.example.repositories.CursoRepository;
import com.example.repositories.EstudianteSistemaRepository;
import com.example.repositories.InfoCursoRepository;
import com.example.repositories.ResumenNotasCicloRepository;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstudianteLoginService {

    private final EstudianteSistemaRepository estudianteSistemaRepository;
    private final ResumenNotasCicloRepository resumenNotasCicloRepository;
    private final CursoRepository cursoRepository;
    private final InfoCursoRepository infoCursoRepository;


    public EstudianteLoginResponse loginYObtenerCursos(Integer codigo, String password, HttpSession session) {

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

        
        //InfoCurso en batch
        List<InfoCurso> infos = cursos.isEmpty()
            ? List.of()
            : infoCursoRepository.findByCursoIn(cursos);//Busca de Curso  su infoCurso

        //Mapear InfoCurso por idCurso
        Map<Integer, InfoCurso> infoMap = infos.stream().collect(Collectors.toMap(ic -> ic.getCurso().getId_curso(), ic -> ic));

        //Convertir a DTO
        List<CursoConInfoResponse> cursosResp = cursos.stream()
            .map(c -> CursoConInfoResponse.of(c, infoMap.get(c.getId_curso())))
            .toList();

        //Construir respuesta
        return new EstudianteLoginResponse(
            estudiante.getCodigo_estudiante(), //Apunta a los atributos de mi record, si modifico el orden en mi record tambien
            carrera.getId_carrera(),
            carrera.getNombreCarrera(),
            ultimoPeriodo,
            nuevoPeriodo,
            cursosResp
        );
    }

    

}
