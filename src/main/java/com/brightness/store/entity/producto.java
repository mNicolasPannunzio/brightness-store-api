package com.brightness.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")

public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "el nombre es obligatorio")
  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(length = 500)
  private String descripcion;

  @NotNull(message = "El precio es obligatorio")
  @Positive(message = "El precio debe ser mayor a 0")
  @Column(nullable = false)
  private BigDecimal precio;

  @NotNull(message = "El stock es obligatorio")
  @PositiveOrZero(message = "El stock no puede ser negativo")
  @Column(nullable = false)
  private Integer stock;

  // Constructor vacio (OBLIGATORIO para JPA)
  public Producto() {
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
