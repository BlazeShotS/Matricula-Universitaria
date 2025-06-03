package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.entidad.Matricula;
import com.example.repositories.MatriculaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    
    
    public List <Matricula> listar(){
        return matriculaRepository.findAll();
    }

    public Matricula insertar (Matricula matricula){
        return matriculaRepository.save(matricula);
    }

    public Matricula actualizar (Integer id , Matricula matricula){
        if (!matriculaRepository.existsById(matricula.getId_matricula())) {
            throw new RuntimeException("No se puede actualizar , la matricula no existe");
        }
        return matriculaRepository.save(matricula);
    }


    //Ponemos void porque void no devuelve nada, es decir este metodo hace algo pero no se espera una respuesta de el
    public void eliminar (Integer id){
        if (!matriculaRepository.existsById(id)) {
            throw new RuntimeException("La matricula con el id: "+id+" no existe");
        }
        matriculaRepository.deleteById(id);
    }



}
