package com.example.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    private Integer id_seccion;


    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    
    private int horario; //Rango 8:30 - 11:30

    @NotNull
    @NotBlank(message = "Ingrese el aula")
    private String aula; //403


    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesores Profesores;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
