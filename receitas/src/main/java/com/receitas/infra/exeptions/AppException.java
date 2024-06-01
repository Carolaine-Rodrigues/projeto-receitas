package com.receitas.infra.exeptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class AppException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity newExceptionHandler(Exception e){
        return new ResponseEntity("É preciso passar um valor para o campo.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity newExceptionHandlerNotFound(Exception e){
        return new ResponseEntity("Verifique a url e tente novamente.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity newExceptionHandlerInternalServer(Exception e){
        return new ResponseEntity("Erro interno de servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(SQLException.class)
    public ResponseEntity newExceptionHandlerSqlError(Exception e){
        return new ResponseEntity("Erro de banco de dados.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity newExceptionHandlerNoSuchElement(Exception e){
        return new ResponseEntity("Verifique o parâmetro da url.", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}