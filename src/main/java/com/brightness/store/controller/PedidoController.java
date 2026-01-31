package com.brightness.store.controller;

import com.brightness.store.entity.Pedido;
import com.brightness.store.service.PedidoService;
import com.brightness.store.dto.PedidoRequest;
import com.brightness.store.dto.PedidoResponse;
import com.brightness.store.mapper.PedidoMapper;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import java.util.List;

// Este controller maneja los endpoints HTTP de pedidos
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
  
  private final PedidoService pedidoService;

  // Inyeccion por constructor
  public PedidoController(PedidoService pPedidoService){
    this.pedidoService = pPedidoService;
  }

  // Endpoint POST para crear un pedido
  @PostMapping
  public PedidoResponse crearPedido(
            @Valid @RequestBody PedidoRequest pRequest){

    // 1. Mandamos el request al service para crear el pedido
    Pedido pedidoGuardado = this.pedidoService.crearPedidoDesdeRequest(pRequest);

    // 2. Convertimos la entidad guardada a un DTO de respuesta
    return PedidoMapper.toResponse(pedidoGuardado);
  }

  @GetMapping
  public List<PedidoResponse> obtenerTodos(){

    // Recuperamos todos los pedidos
    List<Pedido> pedidos = this.pedidoService.obtenerTodos();

    // Convertimos entidades a responses
    return pedidos.stream().map(PedidoMapper::toResponse).toList();
  }

  @GetMapping("/{id}") // El nombre del path variable debe coincidir con el del parametro
  public ResponseEntity<PedidoResponse> obtenerPorId(
                                        @PathVariable("id") Long pId ){
    
    // Buscamos el pedido por id
    Pedido pedido = this.pedidoService.obtenerPorId(pId);

    // Si no existe, devolvemos 404
    if (pedido == null){
      return ResponseEntity.notFound().build();
    }

    // Converimos a response
    PedidoResponse response = PedidoMapper.toResponse(pedido);

    return ResponseEntity.ok(response);

  }

}