package br.com.alura.comex.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;


public class CategoriaDto {

	 private Long id;
	 private String nome;
	 private StatusCategoria status;
	 
	 public CategoriaDto(Categoria categoria) {
		 this.id = categoria.getId();
		 this.nome = categoria.getNome();
		 this.status = categoria.getStatus();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public StatusCategoria getStatus() {
		return status;
	}
	
	public static List<CategoriaDto> converter(List<Categoria> categoria) {
		return categoria.stream().map(CategoriaDto::new).collect(Collectors.toList());
	}
	
}
