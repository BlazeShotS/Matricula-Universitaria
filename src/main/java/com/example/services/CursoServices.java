package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entidad.Curso;
import com.example.repositories.CursoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CursoServices {

    private final CursoRepository cursoRepository;

     public List <Curso> listar(){
        return cursoRepository.findAll();
    }

    public Curso insertar (Curso curso){
        return cursoRepository.save(curso);
    }

    public Curso actualizar (Integer id , Curso curso){
        if (!cursoRepository.existsById(curso.getId_curso())) {
            throw new RuntimeException("No se puede actualizar , el curso no existe");
        }
        return cursoRepository.save(curso);
    }


    //Ponemos void porque void no devuelve nada, es decir este metodo hace algo pero no se espera una respuesta de el
    public void eliminar (Integer id){
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("El curso con el id: "+id+" no existe");
        }
        cursoRepository.deleteById(id);
    }


    
}
