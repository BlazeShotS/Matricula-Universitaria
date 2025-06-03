package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entidad.Seccion;
import com.example.repositories.SeccionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeccionService {

    private final SeccionRepository seccionRepository;

    public List <Seccion> listar(){
        return seccionRepository.findAll();
    }

    public Seccion insertar (Seccion seccion){
        return seccionRepository.save(seccion);
    }

    public Seccion actualizar (Integer id , Seccion seccion){
        if (!seccionRepository.existsById(seccion.getId_seccion())) {
            throw new RuntimeException("No se puede actualizar , la seccion no existe");
        }
        return seccionRepository.save(seccion);
    }


    //Ponemos void porque void no devuelve nada, es decir este metodo hace algo pero no se espera una respuesta de el
    public void eliminar (Integer id){
        if (!seccionRepository.existsById(id)) {
            throw new RuntimeException("La seccion con el id: "+id+" no existe");
        }
        seccionRepository.deleteById(id);
    }

}
