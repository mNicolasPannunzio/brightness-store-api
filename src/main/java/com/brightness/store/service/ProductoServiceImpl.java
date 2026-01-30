package com.brightness.store.service;

import com.brightness.store.entity.Producto;
import com.brightness.store.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

  private final ProductoRepository productoRepository;

  public ProductoServiceImpl(ProductoRepository pProductoRepository){
    this.productoRepository = pProductoRepository;
  }

  @Override
  public List<Producto> obtenerTodos(){
    return productoRepository.findAll();
  }

  @Override
  public Optional<Producto> obtenerPorId(Long pId){
    return productoRepository.findById(pId);
  }

  @Override
  public Producto guardar(Producto pProducto){
    return productoRepository.save(pProducto);
  }

  @Override
  public boolean eliminarPorId(Long pId){
    if(!productoRepository.existsById(pId)){
      return false;
    }

    productoRepository.deleteById(pId);
    return true;
  }
  
}
