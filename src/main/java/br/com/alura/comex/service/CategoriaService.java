package br.com.alura.comex.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.dto.PedidosCategoriaDto;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.controller.form.CategoriaUpdateForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.StatusCategoria;
import br.com.alura.comex.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public CategoriaDto findById(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isPresent()) {
			return new CategoriaDto(categoria.get());
		}
		return null;	
	}
	
	public List<CategoriaDto> findAll(){
		List<Categoria> categorias = categoriaRepository.findAll();
		return CategoriaDto.converter(categorias);
	}
	
	public PedidosCategoriaDto RelatorioPorCategoria(Long id){
		List<ItemDePedido> itemDePedidos = categoriaRepository.findPedidosByCategoria(id); 
		Integer qtd = 0;
		BigDecimal total = null;
		for (ItemDePedido itemDePedido : itemDePedidos) {
			qtd += itemDePedido.getQuantidade();
			total.add(itemDePedido.getValorTotalItem());
		}
		return new PedidosCategoriaDto(itemDePedidos.get(0).getProdutoId().getCategoria().getNome(), qtd, total);
	}
	
	public CategoriaDto deleteById(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isPresent()) {
			categoriaRepository.deleteById(id);
			return new CategoriaDto(categoria.get());
		}		
		return null;
	}
	
	public CategoriaDto save(CategoriaForm categoriaForm) {
		Categoria categoria = new Categoria();
		categoria.setNome(categoriaForm.getNome());
		categoria.setStatus(StatusCategoria.ATIVA);
		return new CategoriaDto(categoriaRepository.save(categoria));
	}
	
	public CategoriaDto update(CategoriaUpdateForm categoriaUpdateForm, Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isPresent()) {
			categoria.get().setNome(categoriaUpdateForm.getNome());
			categoria.get().setStatus(categoriaUpdateForm.getStatus());
			return new CategoriaDto(categoriaRepository.save(categoria.get()));
		}
		
		return null;
	}
	
}
