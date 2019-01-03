package com.github.leofalco.exeptions;

import com.github.leofalco.dto.errors.ErrorDTO;
import com.github.leofalco.dto.errors.FieldErrorMessage;
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
     * @return ErrorDTO
     */
    private static ResponseEntity<ErrorDTO> buildError(HttpStatus status,
                                                       Throwable ex,
                                                       HttpServletRequest request) {
        return buildError(ex.getMessage(), status, ex, request);
    }

    private static ResponseEntity<ErrorDTO> buildError(String customMessage,
                                                       HttpStatus status,
                                                       Throwable ex,
                                                       HttpServletRequest request) {

        ErrorDTO error = ErrorDTO.builder()
                .status(status)
                .method(request.getMethod())
                .path(request.getRequestURI())
                .message(customMessage)
                .error(ex.getClass().getSimpleName())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleObjectAlreadyExistsException(ObjectAlreadyExistsException e,
                                                                       HttpServletRequest request) {
        return buildError(ALREADY_REPORTED, e, request);
    }

    @ExceptionHandler(OperationNotSupertedYetException.class)
    public ResponseEntity<ErrorDTO> handleOperationNotSupertedYetException(OperationNotSupertedYetException e,
                                                                           HttpServletRequest request) {
        return buildError(ALREADY_REPORTED, e, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException e,
                                                                       HttpServletRequest request) {

        String message = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.joining());

        return buildError(message, BAD_REQUEST, e, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> handleDataIntegrityViolationException(DataIntegrityViolationException e,
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
    public ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFoundException e,
                                                                  HttpServletRequest request) {

        String message = e.getMessage().replace("com.github.leofalco.model.", "");

        return buildError(message, NOT_FOUND, e, request);
    }

    @ExceptionHandler(RelacionamentoException.class)
    public ResponseEntity<ErrorDTO> handleRelacionamentoException(RelacionamentoException e,
                                                                  HttpServletRequest request) {
        return ResourceExceptionHandler.buildError(HttpStatus.BAD_REQUEST, e, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                    HttpServletRequest request) {

        List<FieldErrorMessage> detail = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError ->
                        new FieldErrorMessage(fieldError.getField(),
                                fieldError.getDefaultMessage(),
                                fieldError.getRejectedValue()))
                .collect(Collectors.toList());

        ErrorDTO error = ErrorDTO.builder()
                .status(BAD_REQUEST)
                .detalhe(detail)
                .path(request.getRequestURI())
                .message("Erro de Validação")
                .error(e.getClass().getSimpleName())
                .build();

        return ResponseEntity.badRequest().body(error);

    }

    @ExceptionHandler(IdNotNullException.class)
    public ResponseEntity<ErrorDTO> handleIdNotNullException(IdNotNullException e,
                                                             HttpServletRequest request) {
        return buildError(BAD_REQUEST, e, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
                                                                          HttpServletRequest request) {
        return buildError(BAD_REQUEST, e.getRootCause(), request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDTO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,
                                                                                 HttpServletRequest request) {
        return buildError(BAD_REQUEST, e, request);
    }
}