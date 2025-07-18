package com.example.entidad;

import java.util.List;

public record EstudianteLoginResponse(
    Integer codigoEstudiante,
    Integer carreraId,
    String carreraNombre,
    Integer periodoActual,
    Integer periodoMatricula,
    List<CursoConInfoResponse> cursos //Ponemos el record , este record tendra el Curso e infoCurso

) {
    
}
