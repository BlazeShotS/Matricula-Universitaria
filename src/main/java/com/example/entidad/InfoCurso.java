package com.example.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoCurso {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_infoCurso;
    
    private String horaSemanal;

    private Integer credito;

    private String tipo;

    @OneToOne
    @JoinColumn(name = "id_curso") //Nombre de la columna de esta tabla InfoCurso , si quiero tener la referencia de la otra tabla se agrega el referencedColumnName = ""
    private Curso curso;

}
