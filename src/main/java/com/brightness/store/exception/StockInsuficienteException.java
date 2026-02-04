package com.brightness.store.exception;

/**
 * Excepcion de negocio lanzada cuando no hay stock suficiente
 * para uno o mas productos del pedido.
 */

public class StockInsuficienteException extends BadRequestException {
  
  public StockInsuficienteException(String pProductoNombre){
    super("Stock insuficiente para el producto: " + pProductoNombre);
  }
}
