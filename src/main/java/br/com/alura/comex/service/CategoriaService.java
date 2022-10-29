package br.com.alura.comex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.controller.form.CategoriaUpdateForm;
import br.com.alura.comex.model.Categoria;
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
