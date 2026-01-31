package com.brightness.store.exception;

public class PedidoNotFoundException extends RuntimeException{
  
  // Exception especifica del dominio pedidos
  public PedidoNotFoundException(Long pId){
    super("No existe un pedido con id: " + pId);
  }
}
