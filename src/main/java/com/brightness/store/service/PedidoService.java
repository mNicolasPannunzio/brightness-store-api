package com.brightness.store.service;

import com.brightness.store.entity.Pedido;

// Interfaz del servicio de pedidos
public interface PedidoService {
  
  // el service solo recibe entidades, no DTOs
  Pedido crearPedido(Pedido pedido);
}
