package br.com.alura.comex.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PedidoForm {
	
	@NotNull @NotEmpty
	private Long clienteId;

	private List<ItensForm> itensForms;
	
	private String listaIds;
	
	
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getClienteId() {
		return clienteId;
	}
	
	public List<ItensForm> getItensForms() {
		return itensForms;
	}
	
	public void setItensForms(List<ItensForm> itensForms) {
		this.itensForms = itensForms;
	}
	
	public String getListId() {
		this.itensForms.forEach(item -> this.listaIds += item.getProdutoId()+",");
		return listaIds.substring(0, listaIds.length()-1);
	}
	
}
