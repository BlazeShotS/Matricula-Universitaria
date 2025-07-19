package com.example.entidad.procesar.matricula;

import java.util.List;

public record MatriculaRequest(
    Integer codigo_estudiante,
    List<Integer> id_secciones
) {

}
