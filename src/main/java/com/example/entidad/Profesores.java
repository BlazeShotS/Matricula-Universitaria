package com.example.entidad;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_profesor;

    @NotBlank(message = "Ingrese el nombre del profesor")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no puede contener números ni símbolos")
    private String nombre;

    @NotBlank(message = "Ingrese el apellido del profesor")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El apellido no puede contener números ni símbolos")
    private String apellido;

    @NotBlank(message = "Ingrese el correo del profesor")
    @Email(message = "Ingrese un correo válido")
    private String correo;

    @NotBlank(message = "Ingrese el teléfono del profesor")
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

   
    // No relacionamos con la tabla carrera, porque la tabla carrera esta enfocada en las carreras que ofrecen la universidad , y esta es profesion del docente
    @NotBlank(message = "Ingrese la profesion del profesor")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no puede contener números ni símbolos")
    private String profesion;


    @NotBlank(message = "Ingrese el nivel de estudio del docente")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no puede contener números ni símbolos")
    private String nivel_estudio;



}
