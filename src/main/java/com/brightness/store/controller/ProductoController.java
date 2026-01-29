package com.brightness.store.controller;

import com.brightness.store.entity.Producto;
import com.brightness.store.repository.ProductoRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController{

  private final ProductoRepository productoRepository;

  public ProductoController(ProductoRepository productoRepository){
    this.productoRepository = productoRepository;
  }

  @GetMapping
  public List<Producto> obtenerProductos(){
    return productoRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {

    return productoRepository.findById(id)
            .map(producto -> ResponseEntity.ok(producto))
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto){
    
    Producto productoGuardado = productoRepository.save(producto);

    return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){

    if (!productoRepository.existsById(id)){
      return ResponseEntity.notFound().build();
    }

    productoRepository.deleteById(id);
    return ResponseEntity.noContent().build(); //HTTP 204
    
  }


}
