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

    public Usuario selectId(Integer id_usuario) {
        return usuarioRepository.findById(6).orElseThrow(() -> new RuntimeException("No existe el id: " + id_usuario));
    }

    public Usuario insertar (Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar (Integer id_usuario , Usuario usuario){
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

    //Para el SPRING SECURITY (seguridad)
    public Usuario findByCorreo (String correo){
        return usuarioRepository.findByCorreo(correo).orElseThrow();
    }


}
