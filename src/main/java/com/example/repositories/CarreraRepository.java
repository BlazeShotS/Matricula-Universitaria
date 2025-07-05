package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entidad.Carrera;


public interface CarreraRepository extends JpaRepository <Carrera,Integer>{

    Optional<Carrera> findByNombreCarrera(String nombreCarrera); //Buscar por nombre de carrera nombreCarrera tiene que ser igual al de la entidad


}
