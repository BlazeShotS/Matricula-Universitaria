package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entidad.Usuario;
import com.example.repositories.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List <Usuario> listarTodas(){
        return usuarioRepository.findAll();
    }

    public Usuario insertar (Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar (Usuario usuario){
        if (!usuarioRepository.existsById(usuario.getId_usuario())) {
            throw new RuntimeException("No se puede actualizar , el usuario no existe");
        }
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Integer id_usuario){
        if (!usuarioRepository.existsById(id_usuario)) {
            throw new RuntimeException("El usuario" +id_usuario+"no existe");            
        }
        usuarioRepository.deleteById(id_usuario);
    }


}
