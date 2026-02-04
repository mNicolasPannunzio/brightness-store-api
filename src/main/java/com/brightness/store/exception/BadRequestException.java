package com.brightness.store.exception;

// Excepcion base para errores 400 (reglas de negocio)
public class BadRequestException extends RuntimeException {

  public BadRequestException(String pMessage){
    super(pMessage);
  }
  
}
