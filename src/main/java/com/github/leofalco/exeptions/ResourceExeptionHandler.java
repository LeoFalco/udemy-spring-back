package com.github.leofalco.exeptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
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

        StandardError error = new StandardError();

        error.setError(e.getClass().getSimpleName());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.BAD_REQUEST.value());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private static ResponseEntity<StandardError> buildError(HttpStatus status, RuntimeException ex, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setPath(request.getRequestURI());
        error.setMessage(ex.getMessage());
        error.setError(ex.getClass().getSimpleName());

        return ResponseEntity.status(status.value()).body(error);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {


        // deixando excessao com mensagem mais amigável
        String message = Arrays.stream(e.getMostSpecificCause()
                .getMessage()
                .split(" for key"))
                .findFirst()
                .orElse(e.getMostSpecificCause().getMessage());


        StandardError error = new StandardError();

        error.setError(e.getClass().getSimpleName());
        error.setPath(request.getRequestURI());
        error.setMessage(message);
        error.setStatus(HttpStatus.BAD_REQUEST.value());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {

        String message = e.getMessage().replace("com.nelioalves.cursomc.model.", "");

        StandardError error = new StandardError();
        error.setError(e.getClass().getSimpleName());
        error.setPath(request.getRequestURI());
        error.setMessage(message);
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RelacionamentoException.class)
    public ResponseEntity<StandardError> handleRelacionamentoException(RelacionamentoException e, HttpServletRequest request) {
        return ResourceExeptionHandler.buildError(HttpStatus.BAD_REQUEST, e, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ValidationError error = new ValidationError();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getRejectedValue());
        });

        error.setError(e.getClass().getSimpleName());
        error.setPath(request.getContextPath());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IdNotNullExeption.class)
    public ResponseEntity<StandardError> handleIdNotNullExeption(IdNotNullExeption e, HttpServletRequest request) {

        StandardError error = new StandardError();
        error.setError(e.getClass().getSimpleName());
        error.setPath(request.getRequestURI());
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}