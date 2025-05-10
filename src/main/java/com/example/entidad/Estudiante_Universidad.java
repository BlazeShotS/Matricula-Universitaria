package com.example.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante_Universidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dni_estudiante;

    private String nombre;
    private String apellido;
    private String correo;
    private String carrera;
    private Integer telefono;

}
