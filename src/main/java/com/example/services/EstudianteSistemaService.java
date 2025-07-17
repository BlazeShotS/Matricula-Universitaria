package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entidad.EstudianteSistema;
import com.example.entidad.EstudianteUniversidad;
import com.example.repositories.EstudianteSistemaRepository;
import com.example.repositories.EstudianteUniversidadRepository;
import com.example.util.Rol;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstudianteSistemaService {

    private EstudianteSistemaRepository estudianteSistemaRepository;
    private EstudianteUniversidadRepository estudianteUniversidadRepository;

    public List <EstudianteSistema> listarTodas(){
        return estudianteSistemaRepository.findAll();
    }

    //PARA CUANDO EL ESTUDIANTE SE REGISTRA , VALIDA SI ESTA SU DNI EN LA BASE DE DATOS
    public EstudianteSistema insertar (EstudianteSistema estudianteSistema){
        
        Integer dni = estudianteSistema.getEstudiante_Universidad().getDni_estudiante();

        // Verificamos si el estudiante universidad existe
        Optional<EstudianteUniversidad> estudianteUniversidadOpt = estudianteUniversidadRepository.findById(dni);

        if (estudianteUniversidadOpt.isEmpty()) {
            throw new RuntimeException("El DNI ingresado no existe en la base de datos de EstudianteUniversidad");
        }

        // Reasignamos el objeto existente (por si hay diferencias con el que viene del JSON)
        estudianteSistema.setEstudiante_Universidad(estudianteUniversidadOpt.get());
        //Rol por defecto sera ESTUDI
        estudianteSistema.setRol(Rol.ESTUDI);

        return estudianteSistemaRepository.save(estudianteSistema);
    }

    
    public EstudianteSistema obtenerPorCodigoEstudiante (Integer codigo_estudiante){
        return estudianteSistemaRepository.findById(codigo_estudiante).orElseThrow();
    }

    public EstudianteSistema actualizar (Integer codigo_estudiante, EstudianteSistema estudianteSistema){
        if (!estudianteSistemaRepository.existsById(estudianteSistema.getCodigo_estudiante())) {
            throw new RuntimeException("No se encontro el id "+estudianteSistema.getCodigo_estudiante());                        
        }
        return estudianteSistemaRepository.save(estudianteSistema);
    }

    public void eliminar (Integer codigo_estudiante){
        estudianteSistemaRepository.deleteById(codigo_estudiante);
    }

}
