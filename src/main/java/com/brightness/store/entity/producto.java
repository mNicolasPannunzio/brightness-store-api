package com.brightness.store.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")

public class producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nombre;

  @Column(length = 500)
  private String descripcion;

  @Column(nullable = false)
  private BigDecimal precio;

  @Column(nullable = false)
  private Integer stock;

  // Constructor vacio (OBLIGATORIO para JPA)
  public producto() {
  }

  // Getters
  public Long getId(){
    return id;
  }

  public String getNombre(){
    return nombre;
  }

  public String getDescripcion(){
    return descripcion;
  }

  public BigDecimal getPrecio(){
    return precio;
  }

  public Integer getStock(){
    return stock;
  }

  // Setters
  public void setNombre(String pNombre){
    this.nombre = pNombre;
  }

  public void setDescripcion(String pDescripcion){
    this.descripcion = pDescripcion;
  }

  public void setPrecio(BigDecimal pPrecio){
    this.precio = pPrecio;
  }

  public void setStock(Integer pStock){
    this.stock = pStock;
  }

}
