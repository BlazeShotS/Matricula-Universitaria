package com.example.services;

import org.springframework.stereotype.Service;

import com.example.repositories.ProfesoresRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfesoresService {

    private final ProfesoresRepository profesoresRepository;
    

}
