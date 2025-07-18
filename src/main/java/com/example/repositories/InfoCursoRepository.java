package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Curso;
import com.example.entidad.InfoCurso;

public interface InfoCursoRepository extends JpaRepository<InfoCurso, Integer> {
    
    InfoCurso findByCurso(Curso curso);
    List<InfoCurso> findByCursoIn(List<Curso> cursos);//busca todos los registros de InfoCurso cuyo atributo curso esté en la lista de cursos proporcionada.

}
