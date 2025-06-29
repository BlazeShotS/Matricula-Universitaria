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

    @Override                              //username seria mi email
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario;
        try {
            usuario = service.findByCorreo(username);//Busca por email al usuario en la base de datos
            return new CustomUser(usuario); //Envolvemos en CustomUser porque Spring security usa ese CustomUser internamente para validad el PasswordEncoder y el getAuthorities
        } catch (Exception e) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }


    
}
