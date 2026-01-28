package com.brightness.store.controller;

import com.brightness.store.entity.Producto;
import com.brightness.store.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ProductoController{
  
  private final ProductoRepository productoRepository;

  public ProductoController(ProductoRepository productoRepository){
    this.productoRepository = productoRepository;
  }

  @GetMapping("/productos")
  public List<Producto> obtenerProductos(){
    return productoRepository.findAll();
  }

  @PostMapping("/productos")
  public Producto crearProducto(@RequestBody Producto producto){
    return productoRepository.save(producto);
  }


}
