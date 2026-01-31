package com.brightness.store.handler;

import com.brightness.store.exception.PedidoNotFoundException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PedidoNotFoundException.class)
  public ResponseEntity<Map<String, String>> handlePedidoNotFound(
    PedidoNotFoundException pException){

      Map<String, String> error = new HashMap<>();
      error.put("error", "PEDIDO_NO_ENCONTRADO");
      error.put("mensaje", pException.getMessage());

      //Respondemos con HTTP 404 Not Found
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
      MethodArgumentNotValidException pException){
      
      Map<String, String> errores = new HashMap<>();

      // Recorre todos los errores de validacion
      pException.getBindingResult().getFieldErrors().forEach(error -> {
        errores.put(error.getField(),error.getDefaultMessage());
      });

      return ResponseEntity.badRequest().body(errores);
        
    }
    

  
}
