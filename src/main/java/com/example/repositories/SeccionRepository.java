package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Seccion;

public interface SeccionRepository extends JpaRepository <Seccion,Integer>{

    Optional<Seccion> findByAulaAndHorario(String aula, String horario);


}
