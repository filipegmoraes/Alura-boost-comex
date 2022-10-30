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

import br.com.alura.comex.controller.dto.ProdutoDto;
import br.com.alura.comex.controller.dto.ProdutosDto;
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
		return new ProdutoDto(produto);
	}

	public List<ProdutosDto> findAll(int page){
		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Produto> produtos = produtoRepository.findAll(pageable);
		List<ProdutosDto> produtosDto = new ArrayList<>();
		produtos.forEach(produto -> produtosDto.add(new ProdutosDto(produto)));
		return produtosDto;
	}
}
