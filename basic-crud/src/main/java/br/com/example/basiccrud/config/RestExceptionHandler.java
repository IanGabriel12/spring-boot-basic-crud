package br.com.example.basiccrud.config;

import java.util.ArrayList;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.example.basiccrud.exception.ApiError;
import br.com.example.basiccrud.exception.ApiSubError;
import br.com.example.basiccrud.exception.ApiValidationError;
import jakarta.persistence.EntityNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request
    ) {
        String error = "Malformatted JSON object";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<Object> handleEntityNotFound(
        EntityNotFoundException ex
    ) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request
    ) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.setDebugMessage("Check suberrors for details");
        ArrayList<ApiSubError> validationErrors = new ArrayList<ApiSubError>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            validationErrors.add(new ApiValidationError(
                error.getObjectName(),
                error.getField(),
                error.getRejectedValue(),
                error.getDefaultMessage()
            ))
        );
        apiError.setSubErrors(validationErrors);
        return buildResponseEntity(apiError);
    }
}
