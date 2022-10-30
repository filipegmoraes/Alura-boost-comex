package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.ItemDePedido;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Query(value = "select ip.id, ip.preco_unitario, ip.quantidade, ip.produto_id from itens_pedido ip"
			+ "	inner join produtos p on ip.produto_id = p.id"
			+ "	inner join categorias c on p.categoria_id = c.id where c.id = :id", nativeQuery = true)
	List<ItemDePedido> findPedidosByCategoria(Long id);

}
