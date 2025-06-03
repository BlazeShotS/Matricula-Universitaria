package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Curso;

public interface CursoRepository extends JpaRepository <Curso,Integer> {

}
