package br.com.alura.comex.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.comex.controller.dto.ItensDisponiveisDto;
import br.com.alura.comex.controller.dto.PedidoDto;
import br.com.alura.comex.controller.form.PedidoForm;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.TipoDesconto;
import br.com.alura.comex.model.TipoDescontoItem;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.ItemDePedidoRepository;
import br.com.alura.comex.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	ItemDePedidoRepository itemDePedidoRepository;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public PedidoDto save(PedidoForm form) {
		Optional<Cliente> cliente = clienteRepository.findById(form.getClienteId());
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente.get());
		pedido.setTipoDesconto(pedidoRepository.findByClienteId(form.getClienteId()).size() > 5 ? TipoDesconto.FIDELIDADE : TipoDesconto.NENHUM);
		List<ItensDisponiveisDto> produtosDisponiveis = new ArrayList<>();
		produtosDisponiveis = produtoService.findAllById(form.getItensForms(), form.getListId());		
		List<ItemDePedido> itens = new ArrayList<>();
		for (ItensDisponiveisDto itemDePedidoDto : produtosDisponiveis) {
			ItemDePedido itemDePedido = new ItemDePedido(itemDePedidoDto.getQuantidade(), itemDePedidoDto.getProduto());
			itemDePedido.setTipoDesconto(itemDePedido.getQuantidade() > 10 ? TipoDescontoItem.QUANTIDADE : TipoDescontoItem.NENHUM);
			itemDePedido.setPedido(pedido);
			if(itemDePedido.getTipoDesconto() == TipoDescontoItem.QUANTIDADE) {
				itemDePedido.setDesconto(itemDePedido.getValorTotalItem().multiply(new BigDecimal(0.10)));
			}
			itens.add(itemDePedido);
		}
		BigDecimal totalDescontoItens = BigDecimal.ZERO;
		itens.forEach(item -> totalDescontoItens.add(item.getValorTotalItem().subtract(item.getDesconto())));
		if(pedido.getTipoDesconto() == TipoDesconto.FIDELIDADE) {
			BigDecimal totalDesconto = BigDecimal.ZERO;
			totalDesconto.add(totalDescontoItens.multiply(new BigDecimal(0.05)));
			pedido.setDesconto(totalDesconto);
		}
		produtosDisponiveis.forEach(produto -> produto.atualizarEstoque());
		produtosDisponiveis.forEach(produto -> produtoService.produtoRepository.save(produto.getProduto()));
		pedidoRepository.save(pedido);
		itemDePedidoRepository.saveAll(itens);
		return new PedidoDto(itens);
	}
	
	public PedidoDto findById(Long id) {
		Optional<Pedido> optional = pedidoRepository.findById(id);
		if(optional.isPresent()) {
			List<ItemDePedido> itens = itemDePedidoRepository.findByPedidoId(id);
			return new PedidoDto(itens);
		}
		return null;
	}
}
