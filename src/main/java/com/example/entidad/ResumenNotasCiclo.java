package com.example.entidad;

import java.math.BigDecimal;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenNotasCiclo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private BigDecimal promedio_general;

    @NotNull
    @NotBlank(message="Ingrese el merito de orden")
    private String merito;

    private Integer periodo;
    
    //La lectura de relacion , se hace desde la misma entidad, en aca se lee muchos ResumenNotasCiclo pertenecen a una carrera
    @ManyToOne
    @JoinColumn(name = "id_carrera")
    //@JsonIgnoreProperties({"nombre_carrera"}) //OJO CUANDO HAGA INSERT , COMO RESPUESTA NUNCA SE MOSTRARAN LOS DATOS DEL FK MAS QUE EL ID PORQUE SE INSERTA (A MENOS QUE MODIFIQUE EL CONTROLADOR EL METODO INSERT),
    //SIN PONER @JsonIgnoreProperties o @JsonIgnore si se muestran los datos del fk en GET y PUT porque una vez insertado internamente si esta bien la relacion de @ManyToOne etc busca esa relacion y une todo esos datos o informacion al hacer un findAll o finById o ExistById etc
    private Carrera carrera;

    //Se lee muchos ResumenNotasCiclo pertenece a un estudiante sistema
    @ManyToOne
    @JoinColumn(name="codigo_estudiante")
    //@JsonIgnoreProperties({})
    private EstudianteSistema estudianteSistema;


}
