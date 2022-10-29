package br.com.alura.comex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
