package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.EstudianteSistema;
import com.example.entidad.ResumenNotasCiclo;

public interface ResumenNotasCicloRepository extends JpaRepository <ResumenNotasCiclo,Integer> {

    //Filtra los registros donde el campo codigo_estudiante (de la entidad relacionada EstudianteSistema) coincide con el valor proporcionado. y lo ordena de forma descedente el perido si esque tiene varios ResumenNotasCiclo
    //EstudianteSistemaCodigo_estudiante busca los registros que correspondan al estudiante que tiene ese código.
    //findTopBy...OrderByPeriodoDesc le indica a Spring Data JPA que quieres solo el primer resultado después de ordenar por periodo (de mayor a menor).

    //PASAMOS EL OBJETO Y COMO TIENE RELACION CLAVE PK Y FK , JPA BUSCA EL pk que es codigo_estudiante
    Optional<ResumenNotasCiclo> findTopByEstudianteSistemaOrderByPeriodoDesc(EstudianteSistema estudianteSistema);
    
}
