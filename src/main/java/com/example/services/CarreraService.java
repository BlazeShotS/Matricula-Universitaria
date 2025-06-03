package com.example.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.entidad.Carrera;
import com.example.repositories.CarreraRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarreraService {

    private final CarreraRepository carreraRepository;

    public List <Carrera> listar(){
        return carreraRepository.findAll();
    }

    public Carrera insertar (Carrera carrera){
        return carreraRepository.save(carrera);
    }

    public Carrera actualizar (Integer id , Carrera carrera){
        if (!carreraRepository.existsById(carrera.getId_carrera())) {
            throw new RuntimeException("No se puede actualizar , la carrera no existe");
        }
        return carreraRepository.save(carrera);
    }


    //Ponemos void porque void no devuelve nada, es decir este metodo hace algo pero no se espera una respuesta de el
    public void eliminar (Integer id){
        if (!carreraRepository.existsById(id)) {
            throw new RuntimeException("La carrera con el id: "+id+" no existe");
        }
        carreraRepository.deleteById(id);
    }




}
