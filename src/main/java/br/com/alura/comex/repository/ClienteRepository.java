package br.com.alura.comex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
