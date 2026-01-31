package com.brightness.store.service;

import com.brightness.store.entity.Pedido;
import com.brightness.store.entity.PedidoItem;
import com.brightness.store.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import com.brightness.store.exception.PedidoNotFoundException;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
  
  private final PedidoRepository pedidoRepository;

  // Constructor que inyecta el repositorio de pedidos
  public PedidoServiceImpl(PedidoRepository pPedidoRepository){
    this.pedidoRepository = pPedidoRepository;
  }

  @Override
  public Pedido crearPedido(Pedido pPedido) {

    // Aseguramos la relacion bidireccional Pedido <-> PedidoItem
    for (PedidoItem item : pPedido.getItems()) {
        item.setPedido(pPedido);
    }

    // Guardamos el pedido (cascade guarda los items)
    return this.pedidoRepository.save(pPedido);
  }

  @Override
  public List<Pedido> obtenerTodos(){
    // Lectura simple desde el repositorio
    return this.pedidoRepository.findAll();
  }

  @Override
  public Pedido obtenerPorId(Long pId){
    
    // Devuelve el pedido o lanza excepcion si no existe
    return this.pedidoRepository.findById(pId)
        .orElseThrow(() -> new PedidoNotFoundException(pId));
  }


}
