package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entidad.Profesores;
import com.example.repositories.ProfesoresRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfesoresService {

    private final ProfesoresRepository profesoresRepository;


     public List <Profesores> listar(){
        return profesoresRepository.findAll();
    }

    public Profesores insertar (Profesores profesores){
        return profesoresRepository.save(profesores);
    }

    public Profesores obtenerPorId (Integer idProfesor){
        return profesoresRepository.findById(idProfesor).orElse(null);
    }

    public Profesores actualizar (Integer id , Profesores profesores){
        if (!profesoresRepository.existsById(profesores.getId_profesor())) {
            throw new RuntimeException("No se puede actualizar , el profesor no existe");
        }
        return profesoresRepository.save(profesores);
    }


    //Ponemos void porque void no devuelve nada, es decir este metodo hace algo pero no se espera una respuesta de el
    public void eliminar (Integer id){
        if (!profesoresRepository.existsById(id)) {
            throw new RuntimeException("El profesor con el id: "+id+" no existe");
        }
        profesoresRepository.deleteById(id);
    }



}
