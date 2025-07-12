package com.example.services;
    
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.Spring;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.config.JwtService;
import com.example.entidad.CustomUser;
import com.example.entidad.Usuario;
import com.example.repositories.UsuarioRepository;
import com.example.util.AuthenticationRequest;
import com.example.util.AuthenticationResponse;
import com.example.util.RefreshTokenRequest;
import com.example.util.RegisterRequest;
import com.example.util.Rol;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    // El RegisterRequest viene del paquete util
    public String register (RegisterRequest request){
        var user = Usuario.builder()
            .nombre(request.firstname()) //Los nombres como .nombre() , .apellido() etc , tienen que ser los mismos de mi atributo de la entidad
            .apellido(request.lastname())
            .correo(request.email())
            .telefono(request.telefono())
            .password(passwordEncoder.encode(request.password()))
            .rol(Rol.RECEP)
            .build();
        usuarioRepository.save(user);

        return "Usuario registrado exitosamente";
        
        //ENVOLVEMOS el usuario en un CustomUser (Que implementa un UserDetails)
        //CustomUser customUser = new CustomUser(user);

    }

    // EL AuthenticationResponse VIENE DE util, AuthenticationResponse

    // El AuthenticationRequest viene del paquete util (Al momento de iniciar sesion valida credenciales y se genera un token)
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password())); //request.email(), request.password() , correo y contraseña que el usuario ingreso lo envia al AuthenticationManager de SecurityConfig (VALIDA QUE EL USUARIO EXISTA EN LA DATABASEE) y si valida y existe recien pasa a la siguiente linea (ES DECIR ESTA LINEA HACE EL PROCESO DE LOGIN)
        //↑↑ VALIDA QUE EXISTA EN LA DATABASE
        var user = usuarioRepository.findByCorreo(request.email()).orElseThrow(); //Obtiene el correo que el usuario ingreso al iniciar sesion
        
        //ENVOLVEMOS el usuario en un CustomUser (Que implementa un UserDetails)
        CustomUser customUser = new CustomUser(user);

        //Ponemos el customUser en los jwt , porque en nuestro jwtService esta codificado para que los token se guarden en el custonUser que envuelve la entidad Usuario, ademas para el spring security se maneja  solo con customUser
        var jwtToken = jwtService.generateToken(customUser);
        var refreshToken = jwtService.generateRefreshToken(customUser);

        //Me retornara o devolvera esto y nuestro controlador devolvera esto al frontend
        return new AuthenticationResponse(
            jwtToken,
            refreshToken,
            user.getId_usuario(),        // Suponiendo que tienes getId()
            user.getNombre(),           // getNombre()
            user.getRol().name()        // getRol()
        );

        //return new AuthenticationResponse(jwtToken, refreshToken);

    }

    
    // proceso de renovacion del access token usando el refresh token 
    //(es decir cuando se venza el access token , mediante el refresh token se genera un access token y cuando se venca el access token si el refresh token sigo vigente sigue generando un access token hasta que venca el refresh token)
    // El RefreshTokenRequest viene del paquete util ,
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        String userEmail = jwtService.extractUsername(request.refreshToken());
        if (userEmail != null) {
            var user = usuarioRepository.findByCorreo(userEmail).orElseThrow();

            CustomUser customUser = new CustomUser(user);

            if (jwtService.isTookenValid(request.refreshToken(), customUser)) {
                var accessToken = jwtService.generateToken(new HashMap<>(),customUser);
                
                return new AuthenticationResponse(
                    accessToken,
                    request.refreshToken(),
                    user.getId_usuario(),
                    user.getNombre(),
                    user.getRol().name()
                );
            }
        }
        throw new RuntimeException("Token de refrezco invalido");
    }


}
