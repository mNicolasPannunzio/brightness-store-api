package com.brightness.store.service;

import com.brightness.store.entity.Pedido;
import com.brightness.store.entity.PedidoItem;
import com.brightness.store.entity.Producto;
import com.brightness.store.entity.EstadoPedido;
import com.brightness.store.repository.PedidoRepository;
import com.brightness.store.repository.ProductoRepository;
import com.brightness.store.exception.PedidoNotFoundException;
import com.brightness.store.exception.BadRequestException;
import com.brightness.store.exception.CantidadInvalidaException;
import com.brightness.store.exception.StockInsuficienteException;
import com.brightness.store.exception.PedidoSinItemsException;
import com.brightness.store.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

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

  @Transactional
  @Override
  public Pedido crearPedido(Pedido pPedido) {

    // Validamos que el pedido tenga items
    if(pPedido.getItems() == null || pPedido.getItems().isEmpty()){
      throw new PedidoSinItemsException();
    }

    // Recorremos los items del pedido
    for (PedidoItem item : pPedido.getItems()) {

      // Buscamos el producto en DB
      Producto producto = this.productoRepository
                  .findById(item.getProducto().getId())
                  .orElseThrow(() ->
                    new ResourceNotFoundException(
                    "Producto no encontrado con ID " + item.getProducto().getId())
                );     

      // Validacion cantidad
      if (item.getCantidad() <= 0){
        throw new CantidadInvalidaException();
      }

      // Validacion Stock
      if (producto.getStock() < item.getCantidad()){
        throw new StockInsuficienteException(producto.getNombre());
      }

      // Descontamos stock
      producto.setStock(producto.getStock() - item.getCantidad());

      // Asociaciones bidireccionales
      item.setProducto(producto);
      item.setPedido(pPedido);
      item.setPrecioUnitario(producto.getPrecio());
    }

    // Estado inicial del pedido
    pPedido.setEstado(EstadoPedido.CREADO);
    pPedido.setFecha(LocalDateTime.now());

    // Guardamos todo el pedido en cascada
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


  @Transactional
  public Pedido cambiarEstado(Long pId, EstadoPedido pNuevoEstado){

    Pedido pedido = this.obtenerPorId(pId);
    EstadoPedido estadoActual = pedido.getEstado();

    if (pNuevoEstado == EstadoPedido.CANCELADO){
      throw new BadRequestException(
        "Para cancelar un pedido use el endpoint de cancelacion");
      
    }
    
    // Reglas de transicion
    if(estadoActual == EstadoPedido.CREADO && pNuevoEstado == EstadoPedido.PAGADO){

      pedido.setEstado(pNuevoEstado);

    } else if(estadoActual == EstadoPedido.PAGADO &&
        pNuevoEstado == EstadoPedido.ENVIADO){

        pedido.setEstado(pNuevoEstado);

      } else {
              
        throw new BadRequestException(
          "No se puede cambiar el estado de " + estadoActual + " a " + pNuevoEstado);
              
      }

    return this.pedidoRepository.save(pedido);
  }


  @Transactional
  public Pedido cancelarPedido(Long pId){

    Pedido pedido = this.obtenerPorId(pId);

    EstadoPedido estadoActual = pedido.getEstado();

    // Validaciones de negocio
    if (estadoActual == EstadoPedido.CANCELADO){

      throw new BadRequestException("El pedido ya esta cancelado");
    }

    if(estadoActual == EstadoPedido.ENVIADO){

      throw new BadRequestException("No se puede cancelar un pedido enviado");
    }

    // Revertimos stock de los productos
    for (PedidoItem item : pedido.getItems()){

      Producto producto = item.getProducto();
      producto.setStock(producto.getStock() + item.getCantidad());
    }

    // Cambiamos estado a cancelado
    pedido.setEstado(EstadoPedido.CANCELADO);

    return this.pedidoRepository.save(pedido);
  }


  @Override
  public List<Pedido> obtenerPorEstado(EstadoPedido pEstado){

    return this.pedidoRepository.findByEstado(pEstado);
  }


}
