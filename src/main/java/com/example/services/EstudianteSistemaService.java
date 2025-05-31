package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entidad.EstudianteSistema;
import com.example.repositories.EstudianteSistemaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstudianteSistemaService {

    private EstudianteSistemaRepository estudianteSistemaRepository;

    public List <EstudianteSistema> listarTodas(){
        return estudianteSistemaRepository.findAll();
    }

    public EstudianteSistema insertEstudianteSistema (EstudianteSistema estudianteSistema){
        return estudianteSistemaRepository.save(estudianteSistema);
    }

    public EstudianteSistema obtenerPorCodigoEstudiante (Integer codigo_estudiante){
        return estudianteSistemaRepository.findById(codigo_estudiante).orElseThrow();
    }

    public EstudianteSistema updateEstudianteSistema (Integer codigo_estudiante, EstudianteSistema estudianteSistema){
        if (!estudianteSistemaRepository.existsById(estudianteSistema.getCodigo_estudiante())) {
            throw new RuntimeException("No se encontro el id "+estudianteSistema.getCodigo_estudiante());                        
        }
        return estudianteSistemaRepository.save(estudianteSistema);
    }

    public void deleteEstudianteSistema (Integer codigo_estudiante){
        estudianteSistemaRepository.deleteById(codigo_estudiante);
    }

}
