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

  public ProductoController(ProductoService pProductoService){
    this.productoService = pProductoService;
  }

  @GetMapping
  public ResponseEntity<List<Producto>> listar() {
    return ResponseEntity.ok(productoService.obtenerTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
    return this.productoService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Producto> crear(@Valid @RequestBody Producto pProducto) {
    Producto guardado = productoService.guardar(pProducto);
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
