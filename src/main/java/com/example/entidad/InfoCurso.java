package com.example.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String hora_semanal;

    private String credito;

    private String tipo;

}
