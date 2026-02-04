package com.brightness.store.exception;

// Error cuando el pedido no tiene items
public class PedidoSinItemsException extends BadRequestException{

  public PedidoSinItemsException(){
    super("El pedido debe tener al menos un item");
  }
  
}
