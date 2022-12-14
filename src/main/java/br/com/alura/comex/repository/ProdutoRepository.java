package br.com.alura.comex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.comex.model.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {

	@Query(value = "select p.id, p.descricao, p.nome, p.precoUnitario, p.quantidadeEstoque, p.categoria"
			+ " from Produto p where id in(:listId)")
	List<Produto> findAllById(String listId);
}
