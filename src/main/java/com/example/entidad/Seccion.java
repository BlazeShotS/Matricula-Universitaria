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
public class Seccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_seccion;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    private int hora_semanal;
    
    private String profesor;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
