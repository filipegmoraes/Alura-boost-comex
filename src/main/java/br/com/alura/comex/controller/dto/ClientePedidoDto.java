package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Cliente;

public class ClientePedidoDto {

	private Long id;
	private String nome;
	
	public ClientePedidoDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	
}
