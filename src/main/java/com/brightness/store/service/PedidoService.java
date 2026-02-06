package com.brightness.store.service;

import com.brightness.store.entity.Pedido;
import com.brightness.store.entity.EstadoPedido;

import java.util.List;

// Interfaz del servicio de pedidos
public interface PedidoService {
  
  // el service solo recibe entidades, no DTOs
  Pedido crearPedido(Pedido pPedido);

  // Devuelve todos los pedidos
  List<Pedido> obtenerTodos();

  // Devuelve un pedido por id o null si no existe
  Pedido obtenerPorId(Long pId);

  // Cambia el estado de un pedido
  Pedido cambiarEstado(Long pId, EstadoPedido pNuevoEstado);

  // Cancela un pedido y vuelve a sumar el stock de los productos
  Pedido cancelarPedido(Long pId);

  // Devuelve una lista de pedidos dependiendo del estado solicitado
  List<Pedido> obtenerPorEstado(EstadoPedido pEstado);
}
