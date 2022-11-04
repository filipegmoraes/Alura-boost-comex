package br.com.alura.comex.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.model.ItemDePedido;

public class PedidoDto {

	private Long id;
	private LocalDate dataDoPedido;
	private BigDecimal valor;
	private BigDecimal totalDesconto;
	private List<ItensDto> itensDoPedido;
	private ClientePedidoDto cliente;
	
	public PedidoDto(List<ItemDePedido> itens) {
		this.id = itens.get(0).getId();
		this.dataDoPedido = itens.get(0).getPedidoId().getData();
		this.cliente = new ClientePedidoDto(itens.get(0).getPedidoId().getCliente());
		this.itensDoPedido.addAll(itens.stream().map(ItensDto::new).collect(Collectors.toList()));
		this.itensDoPedido.forEach(item -> {
			this.valor.add(item.getValorPago());
			this.totalDesconto.add(item.getDesconto());
		});
	}

	public LocalDate getDataDoPedido() {
		return dataDoPedido;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal getTotalDesconto() {
		return totalDesconto;
	}

	public List<ItensDto> getItensDoPedido() {
		return itensDoPedido;
	}

	public ClientePedidoDto getCliente() {
		return cliente;
	}

	public Long getId() {
		return id;
	}
	
	
}
