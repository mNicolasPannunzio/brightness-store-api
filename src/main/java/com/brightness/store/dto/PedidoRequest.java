package com.brightness.store.dto;

import com.brightness.store.entity.Pedido;
import com.brightness.store.entity.PedidoItem;
import com.brightness.store.entity.Producto;
import com.brightness.store.repository.ProductoRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import java.util.List;

// DTO que representa el pedido recibido por JSON
public class PedidoRequest {

  @NotNull(message = "La lista de items es obligatoria")
  @NotEmpty(message = "El pedido debe tener al menos un item")
  @Valid   // Fuerza a validar cada PedidoItemRequest
  private List<PedidoItemRequest> items;

  // Getters
  public List<PedidoItemRequest> getItems(){
    return this.items;
  }

  //Setters
  public void setItems(List<PedidoItemRequest> pItems){
    this.items = pItems;
  }

  // Convierte el DTO en una entidad Pedido
  public Pedido toEntity(){

    Pedido pedido = new Pedido();
    
    for (PedidoItemRequest itemReq : this.items){

      PedidoItem item = new PedidoItem();

      // Creamos un producto "placeHolder" solo con el ID
      Producto producto = new Producto();
      producto.setId(itemReq.getProductoId());

      item.setProducto(producto);

      // Guardamos solo la cantidad
      item.setCantidad(itemReq.getCantidad());

      // Lo agregamos a la lista sin setear producto aun.
      pedido.getItems().add(item);
    }

    return pedido;
  }


}
