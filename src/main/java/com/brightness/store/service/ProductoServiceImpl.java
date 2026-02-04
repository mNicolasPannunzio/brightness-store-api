package com.brightness.store.service;

import com.brightness.store.entity.Producto;
import com.brightness.store.exception.ResourceNotFoundException;
import com.brightness.store.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

  private final ProductoRepository productoRepository;

  public ProductoServiceImpl(ProductoRepository pProductoRepository){
    this.productoRepository = pProductoRepository;
  }

  @Override
  public List<Producto> obtenerTodos(){
    return this.productoRepository.findAll();
  }

  @Override
  public Producto obtenerPorId(Long pId){
    return this.productoRepository.findById(pId)
        .orElseThrow(() -> new ResourceNotFoundException(
          "Producto no encontrado con ID " + pId));
  }

  @Override
  public Producto guardar(Producto pProducto){
    return this.productoRepository.save(pProducto);
  }

  @Override
  public boolean eliminarPorId(Long pId){
    if(!this.productoRepository.existsById(pId)){
      return false;
    }

    this.productoRepository.deleteById(pId);
    return true;
  }
  
}
