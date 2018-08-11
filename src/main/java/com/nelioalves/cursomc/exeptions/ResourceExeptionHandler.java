package com.nelioalves.cursomc.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExeptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        return ResourceExeptionHandler.buildError(HttpStatus.NOT_FOUND, e, request);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<StandardError> objectAlreadyExistsExeption(ObjectAlreadyExistsException e, HttpServletRequest request) {
        return ResourceExeptionHandler.buildError(HttpStatus.ALREADY_REPORTED, e, request);
    }

    @ExceptionHandler(OperationNotSupertedYetException.class)
    public ResponseEntity<StandardError> operationNotSupertedYetException(OperationNotSupertedYetException e, HttpServletRequest request) {
        return ResourceExeptionHandler.buildError(HttpStatus.ALREADY_REPORTED, e, request);
    }


    private static ResponseEntity<StandardError> buildError(HttpStatus status, RuntimeException ex, HttpServletRequest request) {
        StandardError error = new StandardError(status, ex, request.getRequestURI());
        return ResponseEntity.status(status.value()).body(error);
    }
}
