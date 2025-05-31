package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entidad.Usuario;

public interface UsuarioRepository  extends JpaRepository <Usuario,Integer> {

}
