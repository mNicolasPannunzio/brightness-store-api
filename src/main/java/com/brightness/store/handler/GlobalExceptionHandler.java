package com.brightness.store.handler;

import com.brightness.store.exception.*;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;



@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PedidoNotFoundException.class)
  public ResponseEntity<ApiError> handlePedidoNotFound(
    PedidoNotFoundException pException){

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(new ApiError(HttpStatus.NOT_FOUND, pException.getMessage()));
    
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidationErrors(
    MethodArgumentNotValidException pException){
      
    String mensaje = pException.getBindingResult()
       .getFieldErrors().stream()
       .map(error -> error.getField() + ": " + error.getDefaultMessage())
       .findFirst().orElse("Error de validacion");

    return ResponseEntity.badRequest()
      .body(new ApiError(HttpStatus.BAD_REQUEST, mensaje));
        
    }
    

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ApiError> handleBadRequest(
    BadRequestException pException){

    return ResponseEntity.badRequest()
      .body(new ApiError(HttpStatus.BAD_REQUEST, pException.getMessage()));
  }
  
  
  @ExceptionHandler(StockInsuficienteException.class)
  public ResponseEntity<ApiError> handleStockInsuficiente(
    StockInsuficienteException pException) {

    return ResponseEntity.status(HttpStatus.CONFLICT)
      .body(new ApiError(HttpStatus.CONFLICT, pException.getMessage()));
  }
    
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handleResourceNotFound(
    ResourceNotFoundException pException){

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(new ApiError(HttpStatus.NOT_FOUND, pException.getMessage()));
  }

    

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Map<String, Object>> handleTypeMismatch(
    MethodArgumentTypeMismatchException pException) {

    Map<String, Object> body = new HashMap<>();
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("error", "BAD_REQUEST");
    body.put("message", "Estado de pedido inválido: " + pException.getValue());
    body.put("timestamp", LocalDateTime.now());

    return ResponseEntity.badRequest().body(body);
 }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleAny(Exception pException) {
      
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
               "Ocurrio un error inseperado"));
             
  }

  
}
