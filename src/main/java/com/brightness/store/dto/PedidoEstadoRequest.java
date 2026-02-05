package com.brightness.store.dto;

import com.brightness.store.entity.EstadoPedido;

import jakarta.validation.constraints.NotNull;


public class PedidoEstadoRequest {
  
  @NotNull
  private EstadoPedido estado;


  //Getters
  public EstadoPedido getEstado(){
    return this.estado;
  }

  //Setters
  public void setEstado(EstadoPedido pEstado){
    this.estado = pEstado;
  }
}
