package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.model.ItemDePedido;

public interface ItemDePedidoRepository extends JpaRepository<ItemDePedido, Long>{

	List<ItemDePedido> findByPedido_Id(Long id);
}
