package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entidad.EstudianteUniversidad;
import com.example.repositories.EstudianteUniversidadRepository;


@Service
public class EstudianteUniversidadService {

    //Inyeccion de dependencias por contructor
    private final EstudianteUniversidadRepository repository;

    public EstudianteUniversidadService (EstudianteUniversidadRepository repository){
        this.repository=repository;
    }



    //Mostrar
    public List <EstudianteUniversidad>listarTodas(){
        return repository.findAll();
    }

    //Insertar
    public EstudianteUniversidad insertar (EstudianteUniversidad estudiante_Universidad){
        return repository.save(estudiante_Universidad);
    }

    //Buscar por id
    public EstudianteUniversidad ObtenerPorDni (Integer  dni_estudiante){
        return repository.findById(dni_estudiante).orElse(null);
    }

    //Actualizar
    public EstudianteUniversidad Actualizar (Integer  dni_estudiante, EstudianteUniversidad estudiante_Universidad){
        if (!repository.existsById(estudiante_Universidad.getDni_estudiante())) {
            throw new RuntimeException("No se encontrol el id "+estudiante_Universidad.getDni_estudiante());
        }
        return repository.save(estudiante_Universidad);
                
    }

    //Eliminar
    public void eliminar (Integer dni_Estudiante){
        repository.deleteById(dni_Estudiante);
    }
    


}
