package com.brightness.store.service;

import com.brightness.store.entity.Pedido;
import com.brightness.store.entity.PedidoItem;
import com.brightness.store.repository.PedidoRepository;
import com.brightness.store.dto.PedidoRequest;
import com.brightness.store.repository.ProductoRepository;
import com.brightness.store.exception.PedidoNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
  
  private final PedidoRepository pedidoRepository;
  private final ProductoRepository productoRepository;

  // Constructor que inyecta el repositorio de pedidos
  public PedidoServiceImpl(PedidoRepository pPedidoRepository, 
                           ProductoRepository pProductoRepository){
    this.pedidoRepository = pPedidoRepository;
    this.productoRepository = pProductoRepository;    
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

  public Pedido crearPedidoDesdeRequest(PedidoRequest pRequest){
    
    // Convertimos el request en entidad
    Pedido pedido = pRequest.toEntity(this.productoRepository);

    // Guardamos el pedido usando el servicio
    return this.pedidoRepository.save(pedido);
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
