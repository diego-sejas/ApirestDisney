
package com.example.challengeAlkemy.controller;


import com.example.challengeAlkemy.exception.ErrorApp;
import com.example.challengeAlkemy.exception.InvalidDataException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler
  protected ResponseEntity<ErrorApp> handleException(NoSuchElementException exc) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(httpStatus, exc);
  }

  @ExceptionHandler
  protected ResponseEntity<ErrorApp> handleException(DuplicateKeyException exc) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(httpStatus, exc);
  }

  @ExceptionHandler
  protected ResponseEntity<ErrorApp> handleException(IllegalArgumentException exc) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(httpStatus, exc);
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorApp> handleException(InvalidDataException exc) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    List<String> errors = exc.getResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    return buildResponseEntity(httpStatus, new RuntimeException("Data enviada es invalida"), errors);
  }

  @ExceptionHandler
  protected ResponseEntity<ErrorApp> handleException(MethodArgumentTypeMismatchException exc) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(httpStatus, new RuntimeException("Tipo de Argumento invalido"));
  }

  @ExceptionHandler
  protected ResponseEntity<ErrorApp> handleException(Exception exc) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    return buildResponseEntity(httpStatus, new RuntimeException("Se presento un problema, reporte e intente luego."));
  }

  private ResponseEntity<ErrorApp> buildResponseEntity(HttpStatus httpStatus, Exception exc) {
    return buildResponseEntity(httpStatus, exc, null);
  }

  private ResponseEntity<ErrorApp> buildResponseEntity(HttpStatus httpStatus, Exception exc, List<String> errors) {
    ErrorApp error = new ErrorApp();
    error.setMessage( exc.getMessage());
    error.setStatus(httpStatus.value());
    error.setTimestamp(new Date());
    error.setErrors(errors);
    return new ResponseEntity<>(error, httpStatus);

  }

}
