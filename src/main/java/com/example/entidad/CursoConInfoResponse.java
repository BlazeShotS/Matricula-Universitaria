package com.example.entidad;
//Creacion de un record de respuesta personalizada de Curso e InfoCurso
public record CursoConInfoResponse(
    Integer idCurso,
    String nombre,
    Integer ciclo,
    String horaSemanal,
    Integer credito,
    String tipo
) {

   
    public static CursoConInfoResponse of(Curso curso, InfoCurso info) {
        return new CursoConInfoResponse(
            curso.getId_curso(),
            curso.getNombre(),
            curso.getCiclo(),
            info != null ? info.getHoraSemanal() : null,
            info != null ? info.getCredito() : null,
            info != null ? info.getTipo() : null
        );
    }

}
