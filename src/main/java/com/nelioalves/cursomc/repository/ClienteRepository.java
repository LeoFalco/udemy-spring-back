package com.nelioalves.cursomc.repository;

import com.nelioalves.cursomc.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
