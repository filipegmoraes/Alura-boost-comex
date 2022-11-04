package br.com.alura.comex.controller.dto;

import java.math.BigDecimal;

import br.com.alura.comex.model.ItemDePedido;

public class ItensDto {

	private Long itemId;
	private Long produtoId;
	private String NomeProduto;
	private String NomeCategoria;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal valorPago;
	private BigDecimal desconto;
	
	public ItensDto(ItemDePedido itemDePedido) {
		this.itemId = itemDePedido.getId();
		this.produtoId = itemDePedido.getProdutoId().getId();
		this.NomeProduto = itemDePedido.getProdutoId().getNome();
		this.NomeCategoria = itemDePedido.getProdutoId().getCategoria().getNome();
		this.quantidade = itemDePedido.getQuantidade();
		this.precoUnitario = itemDePedido.getPrecoUnitario();
		this.valorPago = itemDePedido.getValorTotalItem();
		this.desconto = itemDePedido.getDesconto();
	}

	public Long getItemId() {
		return itemId;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public String getNomeProduto() {
		return NomeProduto;
	}

	public String getNomeCategoria() {
		return NomeCategoria;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}
	
	
}
