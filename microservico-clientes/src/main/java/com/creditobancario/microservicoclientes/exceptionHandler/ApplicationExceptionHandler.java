package com.creditobancario.microservicoclientes.exceptionHandler;

import com.creditobancario.microservicoclientes.exception.ClienteNaoEncontradoException;
import com.creditobancario.microservicoclientes.exceptionHandler.model.FieldMessage;
import com.creditobancario.microservicoclientes.exceptionHandler.model.StandardError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StandardError validationError = StandardError.builder()
                                            .erros(criarListaDeErros(ex.getBindingResult()))
                                            .timestamp(LocalDateTime.now())
                                            .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                                            .message(ex.getMessage())
                                            .error("Erro de validação")
                                            .path(((HttpServletRequest)request).getRequestURI())
                                            .build();
        return super.handleExceptionInternal(ex, validationError, headers, status, request);
    }

    @ExceptionHandler({ ClienteNaoEncontradoException.class})
    public ResponseEntity<Object> handleClienteNaoEncontradoException(ClienteNaoEncontradoException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .error("Não encontrado")
                .path((request).getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({ ConstraintViolationException.class})
    public ResponseEntity<Object> handleClienteNaoEncontradoException(ConstraintViolationException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .error("Erro validação dados")
                .erros(ex.getConstraintViolations()
                            .stream()
                            .map(c -> FieldMessage
                                        .builder()
                                        .message(c.getMessage())
                                        .fieldName(c.getMessageTemplate())
                                        .build())
                            .collect(Collectors.toList())
                    )
                .path((request).getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }



    private List<FieldMessage> criarListaDeErros(BindingResult bindingResult) {
        List<FieldMessage> erros = Collections.emptyList();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            erros.add(FieldMessage.builder()
                        .fieldName(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build()
                     );
        }
        return erros;
    }

}
