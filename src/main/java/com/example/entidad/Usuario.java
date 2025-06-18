package com.example.entidad;

import com.example.util.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @NotBlank(message = "Ingrese un Nombre")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "No puede contener numeros")
    @Size(min = 2, max = 50, message = "Ingrese un nombre entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "Ingrese un apellido")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "No puede contener numeros")
    @Size(min = 2, max = 50, message = "Ingrese un apellido entre 2 y 50 caracteres")
    private String apellido;

    @NotBlank(message = "Ingrese telefono")
    @Pattern(regexp="\\d{9}", message="El telefono debe tener 9 digitos")
    private String telefono;

    @NotBlank(message = "Ingrese correo")
    @Email(message = "Formato de correo no valido")
    private String correo;

    @NotBlank(message = "Ingrese contraseña")
    @Size(min = 8, message = "La contraseña debe tener minimo 8 caracteres")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Ingrese rol")
    private Rol rol;


}
