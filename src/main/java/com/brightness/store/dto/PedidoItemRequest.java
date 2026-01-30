package com.brightness.store.dto;

// DTO para cada item del pedido
public class PedidoItemRequest {
  
  private Long productoId;
  private Integer cantidad;

  public Long getProductoId(){
    return this.productoId;
  }

  public void setProductoId(Long pProductoId){
    this.productoId = pProductoId;
  }

  public Integer getCantidad(){
    return this.cantidad;
  }

  public void setCantidad(Integer pCantidad){
    this.cantidad = pCantidad;
  }
}
