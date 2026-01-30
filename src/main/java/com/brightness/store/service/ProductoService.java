package com.brightness.store.service;

import com.brightness.store.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

  List<Producto> obtenerTodos();

  Optional<Producto> obtenerPorId(Long pId);

  Producto guardar(Producto pProducto);

  boolean eliminarPorId(Long pId);
}