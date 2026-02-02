package com.brightness.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
@Table(name = "pedido_items")
public class PedidoItem {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "pedido_id", nullable = false)
  private Pedido pedido;

  @ManyToOne(optional = false)
  @JoinColumn(name = "producto_id",  nullable = false)
  private Producto producto;

  @Column(nullable = false)
  @Min(1)
  private Integer cantidad;

  @Column(nullable = false)
  private BigDecimal precioUnitario;
  // Precio del producto al momento de la compra

  @Transient
  private Long productoId;

  public PedidoItem(){}

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

  public Integer getCantidad(){
    return this.cantidad;
  }

  public BigDecimal getPrecioUnitario(){
    return this.precioUnitario;
  }

  public Long getProductoId(){
    return this.productoId;
  }

  // Setters

  public void setPedido(Pedido pPedido){
    this.pedido = pPedido;
  }

  public void setProducto(Producto pProducto){
    this.producto = pProducto;
  }

  public void setCantidad(Integer pCantidad){
    this.cantidad = pCantidad;
  }

  public void setProductoId(Long pProductoId){
    this.productoId = pProductoId;
  }

  public void setPrecioUnitario(BigDecimal pPrecioUnitario){
    this.precioUnitario = pPrecioUnitario;
  }
}
