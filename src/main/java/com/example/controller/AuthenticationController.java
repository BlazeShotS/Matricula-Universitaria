package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.AuthenticationService;
import com.example.util.AuthenticationRequest;
import com.example.util.AuthenticationResponse;
import com.example.util.RefreshTokenRequest;
import com.example.util.RegisterRequest;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    //El AuthenticationRespones viene del paquete util

    //El RegisterRequest viene de el paquete util , el record RegisterRequest
    @PostMapping("/registro")
    public ResponseEntity <AuthenticationResponse> registrar(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }    

    @PostMapping("/autenticarse")
    public ResponseEntity <AuthenticationResponse> authenticar (@RequestBody AuthenticationRequest request ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    //Con este solicitamos con nuestro token refresh un nuevo token de access
    @PostMapping("/refresh-token")
    public ResponseEntity <?> refreshToken(@RequestBody RefreshTokenRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.refreshToken(request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));
        }
        
    }


}
