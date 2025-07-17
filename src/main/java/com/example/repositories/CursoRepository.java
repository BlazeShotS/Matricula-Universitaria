package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Carrera;
import com.example.entidad.Curso;

public interface CursoRepository extends JpaRepository <Curso,Integer> {
    //Para filtrar por carrera, busca todo los cursos que tenga esa carrera en especifica que fue mandado mediante un id
    List <Curso> findByCarrera(Carrera carrera);
    
    //Primero se va a la entidad Carrera busca el Id_Carrera y despues vuelve a la entidad Curso y busca el ciclo, toma el campo id_carrera de la entidad carrera y lo usa en la consulta de mi entidad curso algo asi,  SELECT * FROM curso WHERE id_carrera = ? AND ciclo = ?
    List<Curso> findByCarreraAndCiclo(Carrera carrera, Integer ciclo);
    
}
