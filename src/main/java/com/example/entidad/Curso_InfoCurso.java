package com.example.entidad;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Curso_InfoCurso(

    //ACA VALIDAREMOS ,  NO EN LAS ENTIDADES PORQUE NUESTRO POST Y PUT USAN ESTE RECORD

    //Curso    
    @NotBlank(message = "Ingrese el nombre del curso")
    @Size(min = 2, max = 70, message = "Ingrese un apellido entre 2 y 50 caracteres")
    String nombre,
    @NotNull(message = "Ingrese el ciclo")
    @Min(value = 1, message = "El ciclo no puede ser menor que 1")
    @Max(value = 10, message = "El ciclo no puede ser mayor que 10")
    Integer ciclo,

    @NotNull(message = "Seleccione una carrera") 
    Integer id_carrera,

    //InfoCurso
    @NotBlank(message = "Ingrese la hora semanal")
    @Pattern(regexp = "^\\d{1,2}(\\.\\d{1,2})?$", message = "Ingrese una hora válida (solo números)")
    String horaSemanal,
    @NotNull(message = "Ingrese los creditos que tiene el curso")
    @Min(value = 1, message = "El crédito debe ser al menos 1")
    @Max(value = 9, message = "El crédito no debe superar 9")
    Integer credito,
    @NotBlank(message = "Ingrese un tipo (Obligatorio/Electivo)")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "No puede contener numeros")
    String tipo 


) {

}
