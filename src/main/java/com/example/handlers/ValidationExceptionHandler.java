package com.example.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //En aca gracias al @Valid que este en el controlador ,cuando los datos no son validos esta parte se activa
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                Map <String , String> errors = new HashMap();   
            ex.getBindingResult().getAllErrors().forEach(
                (error) ->{
                    String campo = ((FieldError) error).getField();//Captura errores por ejemplo , campo: nombre_carrera
                    String mensaje = error.getDefaultMessage(); // mensaje que se puse en la entidad por ejemplo : "No puede contener numeros"
                    errors.put(campo, mensaje);
                });
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST); //devuelvo como cuerpo de la respuesta
        }
        

}
