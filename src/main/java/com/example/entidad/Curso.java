package com.example.entidad;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
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
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_curso;

    @NotBlank(message = "Ingrese el nombre del curso")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "No puede contener numeros")
    @Size(min = 2, max = 70, message = "Ingrese un apellido entre 2 y 50 caracteres")
    private String nombre;

    @NotNull(message = "Ingrese el ciclo")
    @Min(value = 1, message = "El ciclo no puede ser menor que 1")
    @Max(value = 10, message = "El ciclo no puede ser mayor que 10")
    private Integer ciclo;

    @ManyToOne
    @JoinColumn(name = "id_carrera") //Nombre de la columna de esta tabla curso , si quieres tener la referencia de la otra tabla se agrega el referencedColumnName = ""
    private Carrera carrera;

}
