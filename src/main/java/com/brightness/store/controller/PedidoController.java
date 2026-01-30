package com.brightness.store.controller;

import com.brightness.store.entity.Pedido;
import com.brightness.store.service.PedidoService;
import com.brightness.store.dto.PedidoRequest;
import com.brightness.store.repository.ProductoRepository;
import com.brightness.store.dto.PedidoResponse;
import com.brightness.store.mapper.PedidoMapper;
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
  public PedidoResponse crearPedido(@RequestBody PedidoRequest pRequest){

    // 1. Convertimos el request en entidad
    Pedido pedido = pRequest.toEntity(this.productoRepository);

    // 2. Guardamos el pedido usando el servicio
    Pedido pedidoGuardado = this.pedidoService.crearPedido(pedido);

    // 3. Convertimos la entidad guardada a DTO de respuesta
    return PedidoMapper.toResponse(pedidoGuardado);
  }

}