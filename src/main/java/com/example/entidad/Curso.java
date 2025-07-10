package com.example.entidad;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_curso;

    private String nombre;
   
    private Integer ciclo;

    @ManyToOne
    @JoinColumn(name = "id_carrera") //Nombre de la columna de esta tabla curso , si quieres tener la referencia de la otra tabla se agrega el referencedColumnName = ""
    private Carrera carrera;

}
