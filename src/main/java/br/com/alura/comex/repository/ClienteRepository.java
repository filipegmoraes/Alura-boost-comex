package br.com.alura.comex.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.comex.model.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

}
