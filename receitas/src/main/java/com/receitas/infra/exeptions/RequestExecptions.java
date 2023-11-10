package com.receitas.infra.exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RequestExecptions {


    public ResponseEntity error(){

        ExeptionDTO respostaError = new ExeptionDTO ("Verifique as informções fornrcidas", HttpStatus.NOT_FOUND);
        return ResponseEntity.notFound().build();
    }
}
