package br.com.alura.comex.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.controller.dto.CategoriaDto;
import br.com.alura.comex.controller.form.CategoriaForm;
import br.com.alura.comex.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private CategoriaService categoriaService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder){
		CategoriaDto categoriaDto = categoriaService.save(form);
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoriaDto.getId()).toUri();
		return ResponseEntity.created(uri).body(categoriaDto);
	}
	
}
