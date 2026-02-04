package com.brightness.store.exception;

// Error cuando la cantidad es menor o igual a cero
public class CantidadInvalidaException extends BadRequestException {
  
  public CantidadInvalidaException(){
    super("La cantidad debe ser mayor a cero");
  }
}
