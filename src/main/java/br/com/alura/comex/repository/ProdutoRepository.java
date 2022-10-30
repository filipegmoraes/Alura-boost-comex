package br.com.alura.comex.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.comex.model.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {

}
