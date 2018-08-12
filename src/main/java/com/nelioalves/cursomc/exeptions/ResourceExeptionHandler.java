package com.nelioalves.cursomc.exeptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResourceExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<StandardError> handleObjectAlreadyExistsExeption(ObjectAlreadyExistsException e, HttpServletRequest request) {
        return ResourceExeptionHandler.buildError(HttpStatus.ALREADY_REPORTED, e, request);
    }

    @ExceptionHandler(OperationNotSupertedYetException.class)
    public ResponseEntity<StandardError> handleOperationNotSupertedYetException(OperationNotSupertedYetException e, HttpServletRequest request) {
        return ResourceExeptionHandler.buildError(HttpStatus.ALREADY_REPORTED, e, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {

        List<String> messages = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toList());

        StandardError error = new StandardError(HttpStatus.BAD_REQUEST, e, request.getRequestURI(), messages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private static ResponseEntity<StandardError> buildError(HttpStatus status, RuntimeException ex, HttpServletRequest request) {
        StandardError error = new StandardError(status, ex, request.getRequestURI());
        return ResponseEntity.status(status.value()).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {

        List<String> messages = Collections.singletonList(e.getMostSpecificCause().getMessage());


        StandardError error = new StandardError(HttpStatus.BAD_REQUEST, e, request.getRequestURI(), messages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
