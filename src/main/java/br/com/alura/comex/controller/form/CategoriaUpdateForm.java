package br.com.alura.comex.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.comex.model.StatusCategoria;



public class CategoriaUpdateForm {

	@NotNull @NotEmpty @Length(min = 2)
	private String nome;
	
	private StatusCategoria status;

	public String getNome() {
		return nome;
	}

	public StatusCategoria getStatus() {
		return status;
	}

	public void setStatus(StatusCategoria status) {
		this.status = status;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
