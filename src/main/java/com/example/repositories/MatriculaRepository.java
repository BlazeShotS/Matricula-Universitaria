package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Matricula;

public interface MatriculaRepository extends JpaRepository <Matricula,Integer> {

}
