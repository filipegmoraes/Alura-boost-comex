package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Categoria;


public class CategoriaProdutoDto {

	 private Long id;
	 private String nome;
	 
	 public CategoriaProdutoDto(Categoria categoria) {
		 this.nome = categoria.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	
	public static List<CategoriaProdutoDto> converter(List<Categoria> categoria) {
		return categoria.stream().map(CategoriaProdutoDto::new).collect(Collectors.toList());
	}
	
}
