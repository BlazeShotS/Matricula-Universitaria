package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entidad.ResumenNotasCiclo;
import com.example.repositories.ResumenNotasCicloRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumenNotasCicloService {

    private final ResumenNotasCicloRepository resumenNotasCicloRepository;


    public List <ResumenNotasCiclo> listar() {
        return resumenNotasCicloRepository.findAll();
    }


    public ResumenNotasCiclo insertar (ResumenNotasCiclo resumenNotasCiclo){
        return resumenNotasCicloRepository.save(resumenNotasCiclo);
    }

    public ResumenNotasCiclo actualizar (Integer id , ResumenNotasCiclo resumenNotasCiclo){
        if (!resumenNotasCicloRepository.existsById(resumenNotasCiclo.getId())) {
            throw new RuntimeException("No se puede actualizar el resumen de notas del ciclo no existe");
        }
        return resumenNotasCicloRepository.save(resumenNotasCiclo);
    }
    

    public void eliminar(Integer id){
        if (!resumenNotasCicloRepository.existsById(id)) {
            throw new RuntimeException("No se encontrol el resumen de notas del ciclo: "+id);
        }
        resumenNotasCicloRepository.deleteById(id);
    }



}
