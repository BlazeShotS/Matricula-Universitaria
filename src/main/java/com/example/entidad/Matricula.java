package com.example.entidad;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_matricula;

    @ManyToOne
    @JoinColumn(name="codigo_estudiante")
    private EstudianteSistema estudianteSistema;


    @ManyToOne
    @JoinColumn(name = "id_seccion")
    private Seccion seccion;



    @NotNull(message = "Ingrese la fecha de matrícula")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_matricula;


}
