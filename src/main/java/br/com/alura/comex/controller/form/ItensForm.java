package br.com.alura.comex.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItensForm {

	private Long produtoId;
	@Positive @NotNull
	private Integer quantidadeVendida;
	
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public void setQuantidadeVendida(Integer quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public Integer getQuantidadeVendida() {
		return quantidadeVendida;
	}
	
}
