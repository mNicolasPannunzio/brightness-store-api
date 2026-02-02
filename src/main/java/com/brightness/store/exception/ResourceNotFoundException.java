package com.brightness.store.exception;

// Excepcion para recursos inexistentes
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String pMensaje) {
    super(pMensaje);
  }
  
}
