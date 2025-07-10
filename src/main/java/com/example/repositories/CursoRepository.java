package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Carrera;
import com.example.entidad.Curso;

public interface CursoRepository extends JpaRepository <Curso,Integer> {
    List <Curso> findByCarrera(Carrera carrera);//Para filtrar por carrera
}
