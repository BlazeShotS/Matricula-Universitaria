package com.example.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo_estudiante;

    @NotNull
    @NotBlank(message="Ingrese un correo")
    private String correo;

    @NotNull
    @NotBlank(message="Ingrese una contraseña")
    private String password;

    @JoinColumn (name = "dni_estudiante")
    private EstudianteUniversidad estudiante_Universidad;

}
