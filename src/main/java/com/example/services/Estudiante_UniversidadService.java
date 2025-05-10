package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entidad.Estudiante_Universidad;
import com.example.repositories.Estudiante_UniversidadRepository;


@Service
public class Estudiante_UniversidadService {

    //Inyeccion de dependencias por contructor
    private final Estudiante_UniversidadRepository repository;

    public Estudiante_UniversidadService (Estudiante_UniversidadRepository repository){
        this.repository=repository;
    }




    public List <Estudiante_Universidad>listarTodas(){
        return repository.findAll();
    }

    public Estudiante_Universidad insert (Estudiante_Universidad estudiante_Universidad){
        return repository.save(estudiante_Universidad);
    }

    public Estudiante_Universidad ObtenerPorId (Integer  dni_estudiante){
        return repository.findById(dni_estudiante).orElse(null);
    }


    public Estudiante_Universidad ActualizarEstudianteUniversidad (Integer  dni_estudiante, Estudiante_Universidad estudiante_Universidad){
        if (!repository.existsById(estudiante_Universidad.getDni_estudiante())) {
            throw new RuntimeException("No se encontrol el id "+estudiante_Universidad.getDni_estudiante());
        }
        return repository.save(estudiante_Universidad);
                
    }

    public void eliminar (Integer dni_Estudiante){
        repository.deleteById(dni_Estudiante);
    }
    


}
