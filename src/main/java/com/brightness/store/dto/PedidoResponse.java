package com.brightness.store.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {

  private Long id;
  private LocalDateTime fecha;
  private String estado;
  private List<PedidoItemResponse> items;
  
  // Constructor vacio requerido por Jackson
  public PedidoResponse(){

  }

  // Getters
  public Long getId(){
     return this.id;
  }

  public LocalDateTime getFecha(){
    return this.fecha;
  }

  public String getEstado(){
    return this.estado;
  }

  public List<PedidoItemResponse> getItems(){
    return this.items;
  }

  // Setters
  public void setId(Long pId){
    this.id = pId;
  }

  public void setFecha(LocalDateTime pFecha){
    this.fecha = pFecha;
  }

  public void setEstado(String pEstado){
    this.estado = pEstado;
  }

  public void setItems(List<PedidoItemResponse> pItems){
    this.items = pItems;
  }
}
