package com.example.entidad;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class EstudianteUniversidad {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dni_estudiante;

    @NotNull
    @NotBlank(message="Ingrese un nombre")
    @Pattern(regexp = "\\D*",message="No puede contener numeros")
    private String nombre;

    @NotNull
    @NotBlank(message="Ingrese un apellido")
    @Pattern(regexp = "\\D*",message="No puede contener numeros")
    private String apellido;

    @NotNull
    @NotBlank(message="Ingrese un correo")
    private String correo;

    @NotNull
    @NotBlank(message="Ingrese una carrera")
    @Pattern(regexp = "\\D*",message="No puede contener numeros")
    private String carrera;

    @NotNull
    @NotBlank(message = "Ingrese telefono")
    //@Pattern(regexp="\\d{9}", message="El telefono debe tener 9 digitos")
    private String telefono;

    @NotNull
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
    //@PastOrPresent(message= "La fecha no puede ser futura")
    private String fecha_registro;


    @ManyToOne
    @JoinColumn (name="id_usuario")
    //@JsonIgnoreProperties({"nombre", "apellido", "telefono", "correo", "password", "rol"}) //Aca estoy diciendole que los atributos de la entidad Usuario no se mostraran , mas que solo el id, (Pongo todo los atributos menos el id, porque el id si se mostrara)
    private Usuario usuario;
    

}
