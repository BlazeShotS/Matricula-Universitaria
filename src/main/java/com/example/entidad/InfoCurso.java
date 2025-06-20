package com.example.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Ingrese un apellido")
    private String hora_semanal;

    private Integer credito;

    @NotBlank(message = "Ingrese un tipo (Obligatorio/Electivo)")
    private String tipo;

}
