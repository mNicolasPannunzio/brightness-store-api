package com.brightness.store.controller;

import com.brightness.store.entity.Producto;
import com.brightness.store.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


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

  @GetMapping("/productos/{id}")
  public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {

    return productoRepository.findById(id)
            .map(producto -> ResponseEntity.ok(producto))
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/productos")
  public Producto crearProducto(@RequestBody Producto producto){
    return productoRepository.save(producto);
  }


}
