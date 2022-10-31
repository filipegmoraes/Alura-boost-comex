package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Cliente;

public class ClienteDto {
	
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String local;
	
	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.local = cliente.getCidade()+"/"+cliente.getEstado();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getLocal() {
		return local;
	}
	
	
}
