package com.brightness.store.service;

import com.brightness.store.entity.Pedido;
import com.brightness.store.entity.PedidoItem;
import com.brightness.store.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
  
  private final PedidoRepository pedidoRepository;

  // Inyeccion del repositorio
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
}
