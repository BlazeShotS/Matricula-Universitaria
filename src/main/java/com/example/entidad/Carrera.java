package com.example.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_carrera;


    @NotBlank(message = "Ingrese el nombre de una carrera")
    @Pattern (regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "No puede contener numeros")
    private String nombre_carrera;

}
