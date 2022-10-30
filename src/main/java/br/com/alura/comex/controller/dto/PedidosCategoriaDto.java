package br.com.alura.comex.controller.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;

public class PedidosCategoriaDto {

	private String nomeCategoria;
	private int qtdVendida;
	private BigDecimal montanteVendido;
	
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public int getQtdVendida() {
		return qtdVendida;
	}
	public BigDecimal getMontanteVendido() {
		return montanteVendido;
	}
	
	public PedidosCategoriaDto(String nome, int qtdVendida, BigDecimal total) {
		this.nomeCategoria = nome;
		this.qtdVendida = qtdVendida;
		this.montanteVendido = total;
	}
		
}
