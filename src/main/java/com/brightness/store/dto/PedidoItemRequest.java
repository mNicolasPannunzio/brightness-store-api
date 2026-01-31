package com.brightness.store.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
// DTO para cada item del pedido
public class PedidoItemRequest {
  
  @NotNull(message = "El productoId es obligatorio")
  private Long productoId;

  @NotNull(message = "La cantidad es obligatoria")
  @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
  private Integer cantidad;

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
