package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Carrera;
import com.example.entidad.Curso;

public interface CursoRepository extends JpaRepository <Curso,Integer> {
    //Para filtrar por carrera, busca todo los cursos que tenga esa carrera en especifica que fue mandado mediante un id
    List <Curso> findByCarrera(Carrera carrera);
     
    // Trae cursos de una carrera en un ciclo específico Spring generara algo asi WHERE carrera_id = ? AND ciclo = ?.
    List<Curso> findByCarreraAndCiclo(Carrera carrera, Integer ciclo);
}
