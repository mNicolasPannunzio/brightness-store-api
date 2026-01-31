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

  public List<PedidoItemRequest> getItems(){
    return this.items;
  }

  public void setItems(List<PedidoItemRequest> pItems){
    this.items = pItems;
  }

  // Convierte el DTO en una entidad Pedido
  public Pedido toEntity(ProductoRepository pProductoRepository){

    Pedido pedido = new Pedido();
    // Creamos el pedido base (fecha y estado ya se setean en el constructor)

    // Recorremos los items del request
    for (PedidoItemRequest itemReq : this.items){

      // Buscamos el producto en la base de datos
      Producto producto = pProductoRepository.findById(itemReq.getProductoId())
              .orElseThrow(() -> new RuntimeException("Producto no encontrado: "));
              

      // Creamos el item del pedido
      PedidoItem item = new PedidoItem();

      // Asociamos el producto real y la cantidad solicitada por el cliente
      item.setProducto(producto);
      item.setCantidad(itemReq.getCantidad());

      // Congelamos el precio al momento de la compra
      item.setPrecioUnitario(producto.getPrecio());

      // Lo agregamos a la lista
      pedido.getItems().add(item);
    }

    return pedido;
  }


}
