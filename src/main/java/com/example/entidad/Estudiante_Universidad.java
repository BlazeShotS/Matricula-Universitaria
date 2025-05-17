package com.example.entidad;


import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
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

    
    @NotNull
    @NotBlank(message="Ingrese un nombre")
    @Pattern(regexp = "\\D*",message="No puede contener numeros")
    private String nombre;

    @NotNull
    @NotBlank(message="Ingrese un apellido")
    @Pattern(regexp = "\\D*",message="No puede contener numeros")
    private String apellido;

    @NotNull
    @NotBlank(message="Ingrese un correo")
    private String correo;

    @NotNull
    @NotBlank(message="Ingrese una carrera")
    @Pattern(regexp = "\\D*",message="No puede contener numeros")
    private String carrera;

    @NotNull
    private Integer telefono;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
    @PastOrPresent(message= "La fecha no puede ser futura")
    private LocalDateTime fecha_registro;
    

}
