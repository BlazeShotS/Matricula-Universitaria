package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entidad.EstudianteSistema;

public interface EstudianteSistemaRepository extends JpaRepository <EstudianteSistema,Integer> {
    
    //Use @query porque  mi entidad esta en snake_case es decir con _ y usar la consulta JPA se marea cuando la entidad tiene _ , por eso para esto use un @query personalizado

    @Query("SELECT e FROM EstudianteSistema e WHERE e.codigo_estudiante = :codigo AND e.password = :password")
    Optional<EstudianteSistema> login(@Param("codigo") Integer codigo, @Param("password") String password);

}
