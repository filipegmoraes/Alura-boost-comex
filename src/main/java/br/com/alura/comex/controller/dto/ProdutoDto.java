package br.com.alura.comex.controller.dto;

import java.math.BigDecimal;

import br.com.alura.comex.model.Produto;

public class ProdutoDto {

	private Long id; 
    private String nome;
	private String descricao;
    private BigDecimal precoUnitario;
    private int quantidadeEstoque;
    private CategoriaDto categoria;
    
    
    
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.precoUnitario = produto.getPrecoUnitario();
		this.quantidadeEstoque = produto.getQuantidadeEstoque();
		this.categoria = new CategoriaDto(produto.getCategoria());
	}
	public Long getId() {
		return id;
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
	public CategoriaDto getCategoria() {
		return categoria;
	}
    
    
	
}
