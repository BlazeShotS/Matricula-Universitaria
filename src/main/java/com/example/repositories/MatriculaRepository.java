package com.example.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entidad.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

    // Verifica si el estudiante ya está matriculado almenos en 1 seccion
    @Query("""
        SELECT (COUNT(m) > 0)
        FROM Matricula m
        WHERE m.estudianteSistema.codigo_estudiante = :codigoEstudiante
          AND m.seccion.idSeccion = :idSeccion
    """)
    boolean existeMatricula(@Param("codigoEstudiante") Integer codigoEstudiante, @Param("idSeccion") Integer idSeccion);
}
