package com.example.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeccion;

    @NotNull(message = "Debe seleccionar un curso")
    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @NotBlank(message = "Ingrese el horario")
    @Size(max = 30, message = "El horario no debe exceder los 30 caracteres")
    private String horario; //Rango 8:30 - 11:30

  
    @NotBlank(message = "Ingrese el aula")
    @Size(max = 10, message = "El aula no debe exceder los 10 caracteres")
    private String aula; //403

    @NotNull(message = "Debe seleccionar un profesor")
    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesores profesores;
    
    @NotNull(message = "Ingrese la cantidad de cupos")
    @Min(value = 1, message = "Debe haber al menos 1 cupo")
    private Integer cupos;

    @NotBlank(message = "Seleccione una modalidad")
    @Pattern(regexp = "Presencial|Virtual|Semipresencial", message = "Modalidad no válida")
    private String modalidad;

}
