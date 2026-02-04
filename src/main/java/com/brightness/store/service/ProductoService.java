package com.brightness.store.service;

import com.brightness.store.entity.Producto;

import java.util.List;

public interface ProductoService {

  List<Producto> obtenerTodos();

  Producto obtenerPorId(Long pId);

  Producto guardar(Producto pProducto);

  boolean eliminarPorId(Long pId);
}