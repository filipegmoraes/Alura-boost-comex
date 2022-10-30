package br.com.alura.comex.controller.dto;

import java.math.BigDecimal;

import br.com.alura.comex.model.Produto;

public class ProdutosDto {

    private String nome;
	private String descricao;
    private BigDecimal precoUnitario;
    private int quantidadeEstoque;
    private CategoriaProdutoDto categoria;
    
    
    
	public ProdutosDto(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.precoUnitario = produto.getPrecoUnitario();
		this.quantidadeEstoque = produto.getQuantidadeEstoque();
		this.categoria = new CategoriaProdutoDto(produto.getCategoria());
	}

	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public CategoriaProdutoDto getCategoria() {
		return categoria;
	}
    
    
	
}
