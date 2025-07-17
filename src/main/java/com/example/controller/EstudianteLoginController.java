package com.example.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.ResumenNotasCicloService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "api/EstudianteLogin", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EstudianteLoginController {

    private final ResumenNotasCicloService resumenNotasCicloService;

    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        
        return entity;
    }
    
}
