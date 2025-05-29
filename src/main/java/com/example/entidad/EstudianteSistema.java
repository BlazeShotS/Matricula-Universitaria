package com.example.entidad;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteSistema {

    private Integer codigo_estudiante;

    private String correo;

    private String contrasena;

    private Estudiante_Universidad estudiante_Universidad;

}
