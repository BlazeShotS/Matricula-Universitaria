package com.example.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entidad.EstudianteSistema;
import com.example.entidad.Matricula;
import com.example.entidad.Seccion;
import com.example.entidad.SeccionMatriculaInfo;
import com.example.repositories.EstudianteSistemaRepository;
import com.example.repositories.MatriculaRepository;
import com.example.repositories.SeccionRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeccionService {

    private final SeccionRepository seccionRepository;

    private final MatriculaRepository matriculaRepository;

    private final EstudianteSistemaRepository estudianteSistemaRepository;

    public List<Seccion> listar() {
        return seccionRepository.findAll();
    }

    public Seccion insertar(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    public Seccion actualizar(Integer id, Seccion seccion) {
        if (!seccionRepository.existsById(seccion.getId_seccion())) {
            throw new RuntimeException("No se puede actualizar , la seccion no existe");
        }
        return seccionRepository.save(seccion);
    }

    // Ponemos void porque void no devuelve nada, es decir este metodo hace algo
    // pero no se espera una respuesta de el
    public void eliminar(Integer id) {
        if (!seccionRepository.existsById(id)) {
            throw new RuntimeException("La seccion con el id: " + id + " no existe");
        }
        seccionRepository.deleteById(id);
    }

    @Transactional
    public void crearSeccionMatricula(SeccionMatriculaInfo info) {
        try {

            Optional<Seccion> optionalSeccion = seccionRepository.findByAulaAndHorario(info.aula(), info.horario());

            EstudianteSistema estudiante = estudianteSistemaRepository.findById(info.codigo_estudiante())
                    .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

            if (optionalSeccion.isEmpty()) {
                throw new IllegalArgumentException("Seccion no encontrada");
            }

            Seccion seccion = optionalSeccion.get();

            if (seccion.getCupos() <= 0) {
                throw new IllegalStateException("No hay cupos disponibles");
            }

            Matricula matrícula = new Matricula();
            matrícula.setSeccion(seccion);
            matrícula.setFecha_matricula(LocalDate.now());

            matriculaRepository.save(matrícula);

            seccion.setCupos(seccion.getCupos() - 1);
            seccionRepository.save(seccion);

        } catch (Exception e) {
            throw new RuntimeException("Error al crear la matricula");
        }
    }

}
