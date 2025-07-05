package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entidad.Carrera;
import com.example.entidad.Curso;
import com.example.entidad.Curso_InfoCurso;
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


     public List <Curso> listar(){
        return cursoRepository.findAll();
    }

    public Curso insertar (Curso curso){
        return cursoRepository.save(curso);
    }

    public Curso actualizar (Integer id , Curso curso){
        if (!cursoRepository.existsById(curso.getId_curso())) {
            throw new RuntimeException("No se puede actualizar , el curso no existe");
        }
        return cursoRepository.save(curso);
    }


    //Ponemos void porque void no devuelve nada, es decir este metodo hace algo pero no se espera una respuesta de el
    public void eliminar (Integer id){
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("El curso con el id: "+id+" no existe");
        }
        cursoRepository.deleteById(id);
    }


    @Transactional
    public void crearCursoInfoCurso(Curso_InfoCurso info){
        try {
            //Buscar la carrera por id
            Carrera carrera = carreraRepository.findById(info.id_carrera()).orElseThrow(()-> new RuntimeException("Carrera no encontrada"));

            //Crear un curso
            Curso curso = new Curso();
            curso.setNombre(info.nombre());
            curso.setCiclo(info.ciclo());
            curso.setCarrera(carrera);
            Curso aux = cursoRepository.save(curso);

            //Crear el info del curso
            InfoCurso infoCurso = new InfoCurso();
            infoCurso.setHoraSemanal(info.horaSemanal());
            infoCurso.setCredito(info.credito());
            infoCurso.setTipo(info.tipo());
            infoCurso.setCurso(aux);
            infoCursoRepository.save(infoCurso);
                                    

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    
}
