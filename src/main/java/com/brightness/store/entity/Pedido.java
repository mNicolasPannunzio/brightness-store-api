package com.brightness.store.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "pedidos")
public class Pedido {
  
  @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime fecha;

  @Enumerated(EnumType.STRING)
  private EstadoPedido estado;

  @OneToMany(mappedBy = "pedido", 
             cascade = CascadeType.ALL,
             orphanRemoval = true)
  private List<PedidoItem> items;

  public Pedido(){
    this.fecha = LocalDateTime.now();
    this.estado = EstadoPedido.CREADO;
    this.items = new ArrayList<>();
  }

  // Getters

  public Long getId(){
    return this.id;
  }

  public LocalDateTime getFecha(){
    return this.fecha;
  }

  public EstadoPedido getEstado(){
    return this.estado;
  }

  public List<PedidoItem> getItems(){
    return this.items;
  }

  // Setters

  public void setItems(List<PedidoItem> pItems){
    this.items = pItems;
  }

  public void setEstado(EstadoPedido pEstado){
    this.estado = pEstado;
  }
}
