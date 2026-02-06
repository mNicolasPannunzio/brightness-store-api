package com.brightness.store.repository;

import com.brightness.store.entity.Pedido;
import com.brightness.store.entity.EstadoPedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
  
  List<Pedido> findByEstado(EstadoPedido pEstado);
}
