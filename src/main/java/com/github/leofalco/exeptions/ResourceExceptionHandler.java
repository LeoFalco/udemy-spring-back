package com.github.leofalco.exeptions;

import com.github.leofalco.dto.errors.ErrorDto;
import com.github.leofalco.dto.errors.FieldError;
import com.github.leofalco.exeptions.custom.IdNotNullException;
import com.github.leofalco.exeptions.custom.ObjectAlreadyExistsException;
import com.github.leofalco.exeptions.custom.OperationNotSupertedYetException;
import com.github.leofalco.exeptions.custom.RelacionamentoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ResourceExceptionHandler extends ExceptionHandlerExceptionResolver {


    /**
     * use default exception message
     *
     * @param status  http status
     * @param ex      exception
     * @param request request
     * @return ErrorDto
     */
    private static ResponseEntity<ErrorDto> buildError(HttpStatus status,
                                                       Throwable ex,
                                                       HttpServletRequest request) {
        return buildError(ex.getMessage(), status, ex, request);
    }

    private static ResponseEntity<ErrorDto> buildError(String customMessage,
                                                       HttpStatus status,
                                                       Throwable ex,
                                                       HttpServletRequest request) {

        ErrorDto error = ErrorDto.builder()
                .status(status)
                .method(request.getMethod())
                .path(request.getRequestURI())
                .message(customMessage)
                .error(ex.getClass().getSimpleName())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleObjectAlreadyExistsException(ObjectAlreadyExistsException e,
                                                                       HttpServletRequest request) {
        return buildError(ALREADY_REPORTED, e, request);
    }

    @ExceptionHandler(OperationNotSupertedYetException.class)
    public ResponseEntity<ErrorDto> handleOperationNotSupertedYetException(OperationNotSupertedYetException e,
                                                                           HttpServletRequest request) {
        return buildError(ALREADY_REPORTED, e, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException e,
                                                                       HttpServletRequest request) {

        String message = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.joining());

        return buildError(message, BAD_REQUEST, e, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> handleDataIntegrityViolationException(DataIntegrityViolationException e,
                                                                          HttpServletRequest request) {

        // deixando excessão com mensagem mais amigável
        String message = Arrays.stream(e.getMostSpecificCause()
                .getMessage()
                .split(" for key"))
                .findFirst()
                .orElse(e.getMostSpecificCause().getMessage());

        return buildError(message, BAD_REQUEST, e, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException e,
                                                                  HttpServletRequest request) {

        String message = e.getMessage().replace("com.github.leofalco.model.", "");

        return buildError(message, NOT_FOUND, e, request);
    }

    @ExceptionHandler(RelacionamentoException.class)
    public ResponseEntity<ErrorDto> handleRelacionamentoException(RelacionamentoException e,
                                                                  HttpServletRequest request) {
        return ResourceExceptionHandler.buildError(HttpStatus.BAD_REQUEST, e, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDto> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                    HttpServletRequest request) {

        List<FieldError> detail = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError ->
                        new FieldError(fieldError.getField(),
                                fieldError.getDefaultMessage(),
                                fieldError.getRejectedValue()))
                .collect(Collectors.toList());

        ErrorDto error = ErrorDto.builder()
                .status(BAD_REQUEST)
                .detalhe(detail)
                .path(request.getRequestURI())
                .message("Erro de Validação")
                .error(e.getClass().getSimpleName())
                .build();

        return ResponseEntity.badRequest().body(error);

    }

    @ExceptionHandler(IdNotNullException.class)
    public ResponseEntity<ErrorDto> handleIdNotNullException(IdNotNullException e,
                                                             HttpServletRequest request) {
        return buildError(BAD_REQUEST, e, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
                                                                          HttpServletRequest request) {
        return buildError(BAD_REQUEST, e.getRootCause(), request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDto> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,
                                                                                 HttpServletRequest request) {
        return buildError(BAD_REQUEST, e, request);
    }
}