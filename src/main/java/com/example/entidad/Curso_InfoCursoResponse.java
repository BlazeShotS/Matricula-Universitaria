package com.example.entidad;

//Este record sera para lectura y editar y eliminar
public record Curso_InfoCursoResponse(
    //Curso
    Integer id_curso,
    String nombre,
    Integer ciclo,
    Integer id_carrera,
    String nombreCarrera, // <-- Campo adicional solo para mostrar, se logra obtener gracias a esto ya que tiene relacion , curso.getCarrera().getNombreCarrera()

    //infoCurso
    Integer id_infoCurso,
    String horaSemanal,
    Integer credito,
    String tipo

) {

}
