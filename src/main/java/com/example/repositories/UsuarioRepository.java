package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Usuario;
import java.util.List;


public interface UsuarioRepository  extends JpaRepository <Usuario,Integer> {

    Optional <Usuario> findByCorreo(String correo); //Metodo personalizado ,pero el findByCorreo tiene que ir tal cual porque Spring data JPA tiene esa convencion estricta basada en los nombres exactos de los atributos de la entidad
}
