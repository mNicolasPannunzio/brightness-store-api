package com.brightness.store.controller;

import com.brightness.store.entity.Pedido;
import com.brightness.store.service.PedidoService;
import com.brightness.store.dto.PedidoRequest;
import com.brightness.store.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

// Este controller maneja los endpoints HTTP de pedidos
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
  
  private final PedidoService pedidoService;
  private final ProductoRepository productoRepository;

  // Inyeccion por constructor
  public PedidoController(PedidoService pPedidoService, ProductoRepository pProductoRepository){
    this.pedidoService = pPedidoService;
    this.productoRepository = pProductoRepository;
  }

  // Endpoint POST para crear un pedido
  @PostMapping
  public Pedido crearPedido(@RequestBody PedidoRequest pRequest){

    // Convertimos el DTO en entidad Pedido
    Pedido pedido = pRequest.toEntity(productoRepository);

    // Delegamos la logica de negocio al service
    return this.pedidoService.crearPedido(pedido);
  }

}