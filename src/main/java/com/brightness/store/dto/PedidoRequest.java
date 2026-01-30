package com.brightness.store.dto;

import com.brightness.store.entity.Pedido;
import com.brightness.store.entity.PedidoItem;
import com.brightness.store.entity.Producto;
import com.brightness.store.repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;

// DTO que representa el pedido recibido por JSON
public class PedidoRequest {

  private List<PedidoItemRequest> items;

  public List<PedidoItemRequest> getItems(){
    return this.items;
  }

  public void setItems(List<PedidoItemRequest> pItems){
    this.items = pItems;
  }

  // Convierte el DTO en una entidad Pedido
  public Pedido toEntity(ProductoRepository pProductoRepository){

    Pedido pedido = new Pedido();
    List<PedidoItem> pedidoItems = new ArrayList<>();

    // Recorremos los items del request
    for (PedidoItemRequest itemRequest : this.items){

      // Buscamos el producto en la base de datos
      Producto producto = pProductoRepository.findById(itemRequest.getProductoId())
              .orElseThrow(() -> new RuntimeException("Producto no encontrado: " +
              itemRequest.getProductoId())
              );

      // Creamos el item del pedido
      PedidoItem item = new PedidoItem();
      item.setProducto(producto);
      item.setCantidad(itemRequest.getCantidad());

      // Lo agregamos a la lista
      pedidoItems.add(item);
    }

    // Asociamos los items al pedido
    pedido.setItems(pedidoItems);

    return pedido;
  }


}
