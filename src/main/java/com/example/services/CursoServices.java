package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entidad.Carrera;
import com.example.entidad.Curso;
import com.example.entidad.Curso_InfoCurso;
import com.example.entidad.Curso_InfoCursoResponse;
import com.example.entidad.InfoCurso;
import com.example.repositories.CarreraRepository;
import com.example.repositories.CursoRepository;
import com.example.repositories.InfoCursoRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CursoServices {

    private final CarreraRepository carreraRepository;
    private final CursoRepository cursoRepository;
    private final InfoCursoRepository infoCursoRepository;

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Curso insertar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso actualizar(Integer id, Curso curso) {
        if (!cursoRepository.existsById(curso.getId_curso())) {
            throw new RuntimeException("No se puede actualizar , el curso no existe");
        }
        return cursoRepository.save(curso);
    }

    // Ponemos void porque void no devuelve nada, es decir este metodo hace algo
    // pero no se espera una respuesta de el
    public void eliminar(Integer id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("El curso con el id: " + id + " no existe");
        }
        cursoRepository.deleteById(id);
    }

    /*----------------------------------------------------------------------- */

    /* PARA GET , USA EL RECORD Curso_InfoCursoResponse */
    public List<Curso_InfoCursoResponse> listarCursosConInfo() {

        List<Curso> cursos = cursoRepository.findAll(); // Esto traera los cursos con su carrera
        List<Curso_InfoCursoResponse> respuesta = new ArrayList<>();

        for (Curso curso : cursos) {
            InfoCurso info = infoCursoRepository.findByCurso(curso); // Consulta InfoCurso por curso

            if (info != null) {
                respuesta.add(new Curso_InfoCursoResponse(
                        curso.getId_curso(), // Ojito , se mapea los nombres en el record segun la posicion o orden , si yo muevo aca la posicion en mi record Curso_InfoCursoResponse tengo que hacer que coincida
                        curso.getNombre(),
                        curso.getCiclo(),
                        curso.getCarrera().getId_carrera(),
                        curso.getCarrera().getNombreCarrera(), // ← aquí obtienes el nombre para el DTO
                        info.getId_infoCurso(),
                        info.getHoraSemanal(),
                        info.getCredito(),
                        info.getTipo()));
            }
        }

        return respuesta;
    }

    /* PARA POST , USA EL RECORD Curso_Infocurso */
    @Transactional
    public Curso_InfoCursoResponse crearCursoInfoCurso(Curso_InfoCurso info) {
        try {
            // Buscar la carrera por id
            Carrera carrera = carreraRepository.findById(info.id_carrera()).orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

            // Crear un curso
            Curso curso = new Curso();
            curso.setNombre(info.nombre());
            curso.setCiclo(info.ciclo());
            curso.setCarrera(carrera);
            Curso cursoGuardado = cursoRepository.save(curso);

            // Crear el info del curso
            InfoCurso infoCurso = new InfoCurso();
            infoCurso.setHoraSemanal(info.horaSemanal());
            infoCurso.setCredito(info.credito());
            infoCurso.setTipo(info.tipo());
            infoCurso.setCurso(cursoGuardado);
            InfoCurso infoCursoGuardado = infoCursoRepository.save(infoCurso);

            // IMPORTANTE PORQUE DEVOLVER EN Curso_InfoCursoResponse , y es porque en el html renderizo busca el id_curso pero como yo no devuelvo nada o devuelvo Curso_InfoCurso donde alli no esta el id_curso sale undefined o null y NO aparecera en la tabla html lo que acabo de agregar
            // porque angular dice no tiene id_curso no lo renderizo , por eso es importante una vez que se inserta devolver con Curso_InfoCursoResponse que si tiene el id_curso Todo lo insertado lo agregamos en el record Curso_InfoCursoRespone que es lo que servira para mandarle al usuario
            return new Curso_InfoCursoResponse(
                    cursoGuardado.getId_curso(), // Ojito ,se mapea los nombres en el record segun la posicion o orden si yo muevo aca la posicion en mi record Curso_InfoCursoResponse tengo que hacer que coincida
                    cursoGuardado.getNombre(),
                    cursoGuardado.getCiclo(),
                    carrera.getId_carrera(),
                    carrera.getNombreCarrera(),
                    infoCursoGuardado.getId_infoCurso(),
                    infoCursoGuardado.getHoraSemanal(),
                    infoCursoGuardado.getCredito(),
                    infoCursoGuardado.getTipo());

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al crear el curso y su información", e);
        }
    }

    // PARA PUT , USA EL RECORD Curso
    @Transactional
    public void actualizarCursoConInfo(Integer idCurso, Curso_InfoCurso dto) {
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("Curso no encontrado")); // Busca el curso

        curso.setNombre(dto.nombre());
        curso.setCiclo(dto.ciclo());
        Carrera carrera = carreraRepository.findById(dto.id_carrera())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada")); // Busca la carrera
        curso.setCarrera(carrera);// Actualiza la carrera si se cambio o no

        cursoRepository.save(curso);

        InfoCurso info = infoCursoRepository.findByCurso(curso); // Busca el curso asociado con el infoCurso
        if (info != null) {
            info.setHoraSemanal(dto.horaSemanal());
            info.setCredito(dto.credito());
            info.setTipo(dto.tipo());
            infoCursoRepository.save(info);
        }
    }

    @Transactional
    public void eliminarCursoConInfo(Integer idCurso) {
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        InfoCurso info = infoCursoRepository.findByCurso(curso); // Busca ese curso que tiene relacion con su info curso es decir su clave foranea
        if (info != null) {
            infoCursoRepository.delete(info); // Elimina el info del curso
        }

        cursoRepository.delete(curso); // Elimina el curso
    }


    /* PARA FILTRAR POR CARRERA */
    public List<Curso_InfoCursoResponse> buscarPorCarrera(Integer idCarrera) {

        Carrera carrera = carreraRepository.findById(idCarrera).orElseThrow(() -> new RuntimeException("No existe carrera"));
        List<Curso> cursos = cursoRepository.findByCarrera(carrera);


        return cursos.stream().map(curso -> {
            InfoCurso info = infoCursoRepository.findByCurso(curso); // Busca el InfoCurso asociado

            return new Curso_InfoCursoResponse(
                    curso.getId_curso(),
                    curso.getNombre(),
                    curso.getCiclo(),
                    curso.getCarrera().getId_carrera(),
                    curso.getCarrera().getNombreCarrera(),
                    info.getId_infoCurso(),
                    info.getHoraSemanal(),
                    info.getCredito(),
                    info.getTipo());
        }).toList();
    }

}
