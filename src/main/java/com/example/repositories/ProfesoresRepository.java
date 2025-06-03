package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Profesores;

public interface ProfesoresRepository extends JpaRepository <Profesores,Integer> {

}
