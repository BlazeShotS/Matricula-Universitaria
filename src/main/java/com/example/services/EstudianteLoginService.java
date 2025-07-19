package com.example.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.entidad.Carrera;
import com.example.entidad.Curso;
import com.example.entidad.CursoConInfoResponse;
import com.example.entidad.EstudianteLoginResponse;
import com.example.entidad.EstudianteSistema;
import com.example.entidad.InfoCurso;
import com.example.entidad.Matricula;
import com.example.entidad.ResumenNotasCiclo;
import com.example.entidad.Seccion;
import com.example.entidad.procesar.matricula.MatriculaRequest;
import com.example.entidad.procesar.matricula.MatriculaResponse;
import com.example.repositories.CursoRepository;
import com.example.repositories.EstudianteSistemaRepository;
import com.example.repositories.InfoCursoRepository;
import com.example.repositories.MatriculaRepository;
import com.example.repositories.ResumenNotasCicloRepository;
import com.example.repositories.SeccionRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstudianteLoginService {

    private final EstudianteSistemaRepository estudianteSistemaRepository;
    private final ResumenNotasCicloRepository resumenNotasCicloRepository;
    private final CursoRepository cursoRepository;
    private final SeccionRepository seccionRepository;
    private final InfoCursoRepository infoCursoRepository;
    private final MatriculaRepository matriculaRepository;

    /*--------------------------Para el estudiante cuando inicia sesion se mostrara los cursos como la info del curso especifico-------------- */
    public EstudianteLoginResponse loginYObtenerCursos(Integer codigo, String password) {

        EstudianteSistema estudiante = estudianteSistemaRepository.login(codigo, password)
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        // Obtener último resumen del estudiante (con periodo + carrera)
        var ultimoResumenOpt = resumenNotasCicloRepository.findTopByEstudianteSistemaOrderByPeriodoDesc(estudiante);

        if (ultimoResumenOpt.isEmpty()) {
            throw new RuntimeException(
                    "No se encontró historial de ciclos (ResumenNotasCiclo) para el estudiante. Registre un ciclo inicial primero.");
        }

        ResumenNotasCiclo ultimoResumen = ultimoResumenOpt.get();
        int ultimoPeriodo = ultimoResumen.getPeriodo() != null ? ultimoResumen.getPeriodo() : 0;

        // Carrera tomada del último ResumenNotasCiclo
        Carrera carrera = ultimoResumen.getCarrera();
        if (carrera == null) {
            throw new RuntimeException("El último ResumenNotasCiclo no tiene carrera asociada.");
        }

        int nuevoPeriodo = ultimoPeriodo + 1;

        // Traer cursos de esa carrera SOLO del nuevo ciclo
        List<Curso> cursos = cursoRepository.findByCarreraAndCiclo(carrera, nuevoPeriodo);

        // InfoCurso en batch
        List<InfoCurso> infos = cursos.isEmpty()
                ? List.of()
                : infoCursoRepository.findByCursoIn(cursos);// Busca de Curso su infoCurso

        // Mapear InfoCurso por idCurso
        Map<Integer, InfoCurso> infoMap = infos.stream()
                .collect(Collectors.toMap(ic -> ic.getCurso().getId_curso(), ic -> ic));

        // Convertir a DTO, union personalizada de Curso con InfoCurso , mapeo en el
        // record , los metodos personalizados de mi Curso e InfoCurso
        List<CursoConInfoResponse> cursosResp = cursos.stream()
                .map(c -> CursoConInfoResponse.of(c, infoMap.get(c.getId_curso())))
                .toList();

        // Construir respuesta
        return new EstudianteLoginResponse(
                estudiante.getCodigo_estudiante(), // Apunta a los atributos de mi record, si modifico el orden en mi
                                                   // record tambien
                carrera.getId_carrera(),
                carrera.getNombreCarrera(),
                ultimoPeriodo,
                nuevoPeriodo,
                cursosResp // Curso e InfoCurso
        );
    }

    // -------------------Captura el idCurso y muestra las secciones--------------
    public List<Seccion> listarPorCurso(Integer idCurso) {
        // Si quieres validar que el curso exista:
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado: " + idCurso));

        // Forma por objeto
        return seccionRepository.findByCurso(curso);
    }

    // --------------------Transaction para PROCESAR MATRICULA----------------------
    @Transactional
    public List<MatriculaResponse> procesarMatricula(MatriculaRequest request) {

        if (request.codigo_estudiante() == null) {
            throw new IllegalArgumentException("codigo_estudiante es obligatorio.");
        }
        if (request.id_secciones() == null || request.id_secciones().isEmpty()) {
            throw new IllegalArgumentException("id_secciones no debe estar vacío.");
        }

        // Quitar duplicados del request, LISTA DE LOS IDS DE SECCIONES QUE MANDO EL
        // USUARIO ejemplo asi [7, 16, 20 , 12]
        List<Integer> idsUnicos = request.id_secciones().stream().distinct().toList();

        var estudiante = estudianteSistemaRepository.findById(request.codigo_estudiante()).orElseThrow(
                () -> new IllegalArgumentException("Estudiante no encontrado: " + request.codigo_estudiante()));

        // Traer secciones en lote, compara las secciones de la base de datos con las
        // solicitadas
        var secciones = seccionRepository.findAllById(idsUnicos);
        if (secciones.size() != idsUnicos.size()) {
            Set<Integer> existentes = secciones.stream().map(Seccion::getIdSeccion).collect(Collectors.toSet());
            List<Integer> faltantes = idsUnicos.stream().filter(id -> !existentes.contains(id)).toList();
            throw new IllegalArgumentException("Secciones no encontradas: " + faltantes);
        }

        List<MatriculaResponse> respuestas = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        // recorre todas las secciones que el estudiante quiere matricularse y realiza
        // una serie de validaciones y acciones para registrar la matrícula
        for (Seccion seccion : secciones) {
            Integer idSeccion = seccion.getIdSeccion();

            // Si ya está matriculado, usa continue para saltar esa sección y pasar a la
            // siguiente y ya no descontar cupos ni volver a agregar
            boolean ya = matriculaRepository.existeMatricula(request.codigo_estudiante(), idSeccion);
            if (ya) {
                continue;
            }

            if (seccion.getCupos() == null || seccion.getCupos() <= 0) {
                throw new IllegalStateException("Sin cupos en sección " + idSeccion);
            }

            Matricula m = new Matricula();
            m.setEstudianteSistema(estudiante);
            m.setSeccion(seccion);
            m.setFecha_matricula(hoy);
            matriculaRepository.save(m);

            // Decrementar cupo
            seccion.setCupos(seccion.getCupos() - 1);
            seccionRepository.save(seccion);

            respuestas.add(new MatriculaResponse(
                    m.getId_matricula(),
                    seccion.getIdSeccion()));
        }

        return respuestas;
    }

}
