package br.com.alura.comex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.comex.controller.dto.ItensDisponiveisDto;
import br.com.alura.comex.controller.dto.ProdutoDto;
import br.com.alura.comex.controller.dto.ProdutosDto;
import br.com.alura.comex.controller.form.ItensForm;
import br.com.alura.comex.controller.form.ProdutoForm;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;

	public ProdutoDto save(ProdutoForm produtoForm) {
		Optional<Categoria> categoria = categoriaRepository.findById(produtoForm.getCategoria());
		Produto produto = new Produto();
		produto.setCategoria(categoria.get());
		produto.setDescricao(produtoForm.getDescricao());
		produto.setNome(produtoForm.getNome());
		produto.setPrecoUnitario(produtoForm.getPrecoUnitario());
		produto.setQuantidadeEstoque(produtoForm.getQuantidadeEstoque());	
		return new ProdutoDto(produtoRepository.save(produto));
	}
	
	public ProdutoDto update(ProdutoForm updateForm, Long id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		Optional<Categoria> categoria = categoriaRepository.findById(updateForm.getCategoria());
		optional.get().setCategoria(categoria.get());
		optional.get().setDescricao(updateForm.getDescricao());
		optional.get().setNome(updateForm.getNome());
		optional.get().setPrecoUnitario(updateForm.getPrecoUnitario());
		optional.get().setQuantidadeEstoque(updateForm.getQuantidadeEstoque());
		return new ProdutoDto(produtoRepository.save(optional.get()));	
	}

	public List<ProdutosDto> findAll(int page){
		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Produto> produtos = produtoRepository.findAll(pageable);
		List<ProdutosDto> produtosDto = new ArrayList<>();
		produtos.forEach(produto -> produtosDto.add(new ProdutosDto(produto)));
		return produtosDto;
	}
	
	public ProdutoDto findById(Long id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if(optional.isPresent()) {
			return new ProdutoDto(optional.get());
		}
		return null;
	}
	
	public List<ItensDisponiveisDto> findAllById(List<ItensForm> itens, String listId){
		List<Produto> produtos = new ArrayList<>();
		produtos = produtoRepository.findAllById(listId);
		List<ItensDisponiveisDto> produtosDisponiveis = new ArrayList<>();
		if(produtos.size() == itens.size()) {
			for (Produto produto : produtos) {
				for (ItensForm item : itens) {
					if(item.getProdutoId() == produto.getId()) {
						if(produto.getQuantidadeEstoque() > item.getQuantidadeVendida()) {
							produtosDisponiveis.add( new ItensDisponiveisDto(produto, item.getQuantidadeVendida()));
						}
					}
				}
			}
			return produtosDisponiveis;			
		}
		return null;
	}
	
	public ProdutoDto deleteById(Long id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if(optional.isPresent()) {
			produtoRepository.delete(optional.get());
			return new ProdutoDto(optional.get());
		}
		return null;
	}
	
	
}
