package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Produto;

public class ItensDisponiveisDto {

	private Produto produto;
	private Integer quantidade;
	
	public ItensDisponiveisDto(Produto produto, Integer quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void atualizarEstoque() {
		this.produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - this.quantidade);
	}

	
}
