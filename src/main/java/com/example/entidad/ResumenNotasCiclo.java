package com.example.entidad;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenNotasCiclo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private BigDecimal promedio_general;

    @NotNull
    @NotBlank(message="Ingrese el merito de orden")
    private String merito;

    private Integer periodo;
    
    @ManyToOne
    @JoinColumn(name = "id_carrera")
    //@JsonIgnoreProperties({"nombre_carrera"})
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name="codigo_estudiante")
    //@JsonIgnoreProperties({})
    private EstudianteSistema estudianteSistema;


}
