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
  public Pedido crearPedido(Pedido pedido) {

    // Aseguramos la relacion bidireccional Pedido <-> PedidoItem
    for (PedidoItem item : pedido.getItems()) {
        item.setPedido(pedido);
    }

    // Guardamos el pedido (cascade guarda los items)
    return pedidoRepository.save(pedido);
  }
}
