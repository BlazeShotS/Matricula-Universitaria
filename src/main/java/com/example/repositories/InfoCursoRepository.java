package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Curso;
import com.example.entidad.InfoCurso;

public interface InfoCursoRepository extends JpaRepository<InfoCurso, Integer> {
    InfoCurso findByCurso(Curso curso);
}
