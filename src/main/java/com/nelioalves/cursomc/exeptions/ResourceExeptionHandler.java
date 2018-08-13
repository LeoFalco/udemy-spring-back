package com.nelioalves.cursomc.exeptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
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

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < messages.size() - 1; i++) {
            builder.append(messages.get(i)).append(", ");
        }
        builder.append(messages.get(messages.size() - 1));

        StandardError error = new StandardError(HttpStatus.BAD_REQUEST, e, request.getRequestURI(), builder.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private static ResponseEntity<StandardError> buildError(HttpStatus status, RuntimeException ex, HttpServletRequest request) {
        StandardError error = new StandardError(status, ex, request.getRequestURI());
        return ResponseEntity.status(status.value()).body(error);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {

        String message = e.getMostSpecificCause().getMessage();

        StandardError error = new StandardError(HttpStatus.BAD_REQUEST, e, request.getRequestURI(), message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        return ResourceExeptionHandler.buildError(HttpStatus.NOT_FOUND, e, request);
    }

    @ExceptionHandler(RelacionamentoException.class)
    public ResponseEntity<StandardError> handleRelacionamentoException(RelacionamentoException e, HttpServletRequest request) {
        return ResourceExeptionHandler.buildError(HttpStatus.BAD_REQUEST, e, request);
    }

}