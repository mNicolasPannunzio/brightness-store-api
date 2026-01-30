package com.brightness.store.repository;

import com.brightness.store.entity.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long>{
  
}
