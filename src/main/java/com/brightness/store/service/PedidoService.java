package com.brightness.store.service;

import com.brightness.store.entity.Pedido;
import com.brightness.store.dto.PedidoRequest;

import java.util.List;

// Interfaz del servicio de pedidos
public interface PedidoService {
  
  // el service solo recibe entidades, no DTOs
  Pedido crearPedido(Pedido pPedido);

  Pedido crearPedidoDesdeRequest(PedidoRequest pRequest);

  // Devuelve todos los pedidos
  List<Pedido> obtenerTodos();

  // Devuelve un pedido por id o null si no existe
  Pedido obtenerPorId(Long pId);
}
