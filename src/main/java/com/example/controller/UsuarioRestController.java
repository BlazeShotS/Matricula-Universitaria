package com.example.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entidad.Usuario;
import com.example.services.UsuarioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(value = "api/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    @GetMapping("lista")
    public List <Usuario> selectUsuario() {
        return usuarioService.listarTodas();
    }

    @GetMapping("lista/{id}")
    public Usuario selectIdUsuario(@PathVariable Integer id_usuario) {
        return usuarioService.selectId(id_usuario);
    }

    @PostMapping("insertar")
    public Usuario insertarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.insertar(usuario);
    }


    @PutMapping("actualizar/{id_usuario}")
    public Usuario actualizarUsuario (@PathVariable Integer id_usuario,@Valid @RequestBody Usuario usuario) { //Poner el @Valid hace que se active la validacion automatica de un objeto (En este caso usuario) segun las anotaciones definidas como @NotBlank @Email @Pattern @Size etc
        usuario.setId_usuario(id_usuario); //voy a asegurarme que el objeto usuario que viene en el @RequestBody tenga el mismo ID que viene en la URL
        return usuarioService.actualizar(id_usuario, usuario);
    }


    @DeleteMapping(value = "eliminar/{id_usuario}")
    public ResponseEntity<String> eliminarIdUser(@PathVariable Integer id_usuario) {
        usuarioService.eliminar(id_usuario);
        return ResponseEntity.ok("Usuario eliminado correctamente Id :" + id_usuario);
    }


    //-------Página de acceso para usuarios publicos y privados--------
    @GetMapping("/publico")
    public String paginaPublica() {
        return "Pagina Pública";
    }

    @GetMapping("/privado")
    public String paginaPrivada() {
        return "Página Privada solo con accesos";
    }
    
}
