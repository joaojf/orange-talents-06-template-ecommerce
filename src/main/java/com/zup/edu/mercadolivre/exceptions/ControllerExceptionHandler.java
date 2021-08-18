package com.zup.edu.mercadolivre.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exceptionHandler(MethodArgumentNotValidException e, BindingResult result) {
        if (result.hasErrors()) {
            List<ErrorHandler> errorHandlers = result.getFieldErrors()
                    .stream()
                    .map(ErrorHandler::new)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorHandlers);
        }
        return null;
    }

}
