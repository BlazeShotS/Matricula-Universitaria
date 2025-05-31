package com.example.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.Usuario;
import com.example.services.UsuarioService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "api/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    @GetMapping("lista")
    public List <Usuario> selectUsuario() {
        return usuarioService.listarTodas();
    }


    @PostMapping("insertar")
    public Usuario insertarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.insertar(usuario);
    }
    


    


}
