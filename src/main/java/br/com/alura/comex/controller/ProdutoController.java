package br.com.alura.comex.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.controller.dto.ProdutoDto;
import br.com.alura.comex.controller.dto.ProdutosDto;
import br.com.alura.comex.controller.form.ProdutoForm;
import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

	
	private ProdutoService produtoService;
	
	private CategoriaService categoriaService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder){
		if(categoriaService.findById(form.getCategoria()) != null) {
			ProdutoDto produtoDto = produtoService.save(form);
			URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produtoDto.getId()).toUri();
			return ResponseEntity.created(uri).body(produtoDto);			
		}
		
		return ResponseEntity.badRequest().header("Error", "Categoria Invalida").build();
	}
	
	@GetMapping
	public List<ProdutosDto> Listar(int page){
		return produtoService.findAll(page);
	}
	
}
