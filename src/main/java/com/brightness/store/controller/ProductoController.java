package com.brightness.store.controller;

import com.brightness.store.entity.Producto;
import com.brightness.store.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController{

  private final ProductoService productoService;

  public ProductoController(ProductoService productoService){
    this.productoService = productoService;
  }

  @GetMapping
  public ResponseEntity<List<Producto>> listar() {
    return ResponseEntity.ok(productoService.obtenerTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
    return productoService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto) {
    Producto guardado = productoService.guardar(producto);
    return ResponseEntity.status(201).body(guardado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    boolean eliminado = productoService.eliminarPorId(id);

    if (!eliminado) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }

}
