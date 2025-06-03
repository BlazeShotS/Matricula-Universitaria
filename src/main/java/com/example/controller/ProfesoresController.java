package com.example.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.Entity;

@Entity
@RequestMapping(value = "api/profesores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfesoresController {

    

}
