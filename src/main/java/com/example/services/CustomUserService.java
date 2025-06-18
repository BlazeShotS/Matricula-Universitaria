package com.example.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entidad.CustomUser;
import com.example.entidad.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService{

    private final UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario;
        try {
            usuario = service.findByCorreo(username);
            return new CustomUser(usuario); //El usuario capturado lo mandamos a CustomUser (Sirve tambien para decirle como cargar al usuario desde la DB , en este caso un objeto UserDetails)
        } catch (Exception e) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }


    
}
