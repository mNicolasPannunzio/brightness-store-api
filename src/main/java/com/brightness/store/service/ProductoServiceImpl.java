package com.brightness.store.service;

import com.brightness.store.entity.Producto;
import com.brightness.store.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

  private final ProductoRepository productoRepository;

  public ProductoServiceImpl(ProductoRepository productoRepository){
    this.productoRepository = productoRepository;
  }

  @Override
  public List<Producto> obtenerTodos(){
    return productoRepository.findAll();
  }

  @Override
  public Optional<Producto> obtenerPorId(Long id){
    return productoRepository.findById(id);
  }

  @Override
  public Producto guardar(Producto producto){
    return productoRepository.save(producto);
  }

  @Override
  public boolean eliminarPorId(Long id){
    if(!productoRepository.existsById(id)){
      return false;
    }

    productoRepository.deleteById(id);
    return true;
  }
  
}
