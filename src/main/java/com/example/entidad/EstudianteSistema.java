package com.example.entidad;


import com.example.util.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteSistema {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo_estudiante;

    @NotNull
    @NotBlank(message="Ingrese un correo estudiantil") 
    private String correo_estudiante;

    @NotNull
    @NotBlank(message="Ingrese una contraseña")
    private String password;

    @OneToOne
    @JoinColumn (name = "dni_estudiante")
    //@JsonIgnoreProperties(value={"nombre", "apellido", "correo", "carrera", "telefono", "fecha_registro","usuario"})
    private EstudianteUniversidad estudiante_Universidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Ingrese rol")
    private Rol rol;


}
