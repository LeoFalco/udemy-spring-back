package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.exeptions.ObjectAlreadyExistsException;
import com.nelioalves.cursomc.exeptions.ObjectNotFoundException;
import com.nelioalves.cursomc.exeptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExeptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundExeption(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.NOT_FOUND, e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<StandardError> objectAlreadyExistsExeption(ObjectAlreadyExistsException e, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.ALREADY_REPORTED, e);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED.value()).body(error);
    }
}
