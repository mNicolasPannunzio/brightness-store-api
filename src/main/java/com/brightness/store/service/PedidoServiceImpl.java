package com.brightness.store.service;

import com.brightness.store.entity.Pedido;
import com.brightness.store.entity.PedidoItem;
import com.brightness.store.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
  
  private final PedidoRepository pedidoRepository;

  public PedidoServiceImpl(PedidoRepository pPedidoRepository){
    this.pedidoRepository = pPedidoRepository;
  }

  @Override
  public Pedido crearPedido(Pedido pPedido){

    //Aseguramos relacion bidireccional
    for(PedidoItem item : pPedido.getItems()){
      item.setPedido(pPedido);
    }

    return this.pedidoRepository.save(pPedido);
  }
}
