package com.brightness.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "pedido_items")
public class PedidoItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "pedido_id", nullable = false)
  private Pedido pedido;

  @ManyToOne
  @JoinColumn(name = "producto_id",  nullable = false)
  private Producto producto;

  @Min(1)
  private int cantidad;

  // Getters

  public Long getId(){
    return this.id;
  }

  public Pedido getPedido(){
    return this.pedido;
  }

  public Producto getProducto(){
    return this.producto;
  }

  public int getCantidad(){
    return this.cantidad;
  }

  // Setters

  public void setPedido(Pedido pPedido){
    this.pedido = pPedido;
  }

  public void setProducto(Producto pProducto){
    this.producto = pProducto;
  }

  public void setCantidad(int pCantidad){
    this.cantidad = pCantidad;
  }
}
