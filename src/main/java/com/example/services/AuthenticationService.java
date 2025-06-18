package com.example.services;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entidad.CustomUser;
import com.example.entidad.Usuario;
import com.example.repositories.UsuarioRepository;

import config.JwtService;
import lombok.RequiredArgsConstructor;
import util.AuthenticationRequest;
import util.AuthenticationResponse;
import util.RegisterRequest;
import util.Rol;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // EL AuthenticationResponse VIENE DE util, AuthenticationResponse

    // El RegisterRequest viene del paquete util , RegisterRequest (Registrar un usuario y se genera un token (acces y refresh))
    public AuthenticationResponse register (RegisterRequest request){
        var user = Usuario.builder()
            .nombre(request.firstname()) //Los nombres como .nombre() , .apellido() etc , tienen que ser los mismos de mi atributo de la entidad
            .apellido(request.lastname())
            .correo(request.email())
            .telefono(request.telefono())
            .password(passwordEncoder.encode(request.password()))
            .rol(Rol.RECEP)
            .build();
        usuarioRepository.save(user);

        //ENVOLVEMOS el usuario en un CustomUser (Que implementa un UserDetails)
        CustomUser customUser = new CustomUser(user);

        //Usamos el metodo correcto con los argumentos esperados
        var jwtToken = jwtService.generateToken(new HashMap<>(),customUser);
        var refreshToken = jwtService.generateRefreshToken(customUser);

        return new AuthenticationResponse(jwtToken, refreshToken);

    }


    // El AuthenticationRequest viene del paquete util (Al momento de iniciar sesion valida credenciales y se genera un token)
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())); //request.email(), request.password() , correo y contraseña que el usuario ingreso lo envia al authenticationManager
        var user = usuarioRepository.findByCorreo(request.email()).orElseThrow(); //Obtiene el correo que el usuario ingreso al iniciar sesion
        
        CustomUser customUser = new CustomUser(user);

        var jwtToken = jwtService.generateToken(new HashMap<>(),customUser);
        var refreshToken = jwtService.generateRefreshToken(customUser);
        return new AuthenticationResponse(jwtToken, refreshToken);

    }







}
