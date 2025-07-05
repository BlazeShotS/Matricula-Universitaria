package com.example.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class InfoCurso {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_infoCurso;

    @NotBlank(message = "Ingrese la hora semanal")
    @Pattern(regexp = "^\\d{1,2}(\\.\\d{1,2})?$", message = "Ingrese una hora válida (solo números)")
    private String hora_semanal;

    @NotNull(message = "Ingrese el crédito")
    @Min(value = 1, message = "El crédito debe ser al menos 1")
    @Max(value = 20, message = "El crédito no debe superar 20")
    private Integer credito;

    @NotBlank(message = "Ingrese un tipo (Obligatorio/Electivo)")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "No puede contener numeros")
    private String tipo;

    @OneToOne
    @JoinColumn(name = "id_curso") //Nombre de la columna de esta tabla InfoCurso , si quieres tener la referencia de la otra tabla se agrega el referencedColumnName = ""
    private Curso curso;

}
