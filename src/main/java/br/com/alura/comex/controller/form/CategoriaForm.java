package br.com.alura.comex.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;



public class CategoriaForm {

	@NotNull @NotEmpty @Length(min = 2)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
