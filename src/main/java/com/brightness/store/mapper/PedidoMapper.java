package com.brightness.store.mapper;

import com.brightness.store.dto.PedidoItemResponse;
import com.brightness.store.dto.PedidoResponse;
import com.brightness.store.entity.Pedido;

import java.util.stream.Collectors;

public class PedidoMapper {

  // Convierte una entidad Pedido a un DTO de resouesta
  public static PedidoResponse toResponse(Pedido pPedido){

    PedidoResponse response = new PedidoResponse();
    response.setId(pPedido.getId());
    response.setFecha(pPedido.getFecha());
    response.setEstado(pPedido.getEstado().name());

    response.setItems(pPedido.getItems()
      .stream()
      .map(item -> {
        PedidoItemResponse itemResponse = new PedidoItemResponse();
        itemResponse.setProductoId(item.getProducto().getId());
        itemResponse.setCantidad(item.getCantidad());
        return itemResponse;
      })
      .collect(Collectors.toList())
  );

  return response;
  }
  
}
