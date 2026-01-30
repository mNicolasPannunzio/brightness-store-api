package com.brightness.store.dto;


public class PedidoItemResponse {
  
  private Long productoId;
  private Integer cantidad;

  public PedidoItemResponse(){

  }


  // Getters
  public Long getProductoId(){
    return this.productoId;
  }

  public Integer getCantidad(){
    return this.cantidad;
  }

  // Setters
  public void setProductoId(Long pProductoId){
    this.productoId = pProductoId;
  }

  public void setCantidad(Integer pCantidad){
    this.cantidad = pCantidad;
  }
}
