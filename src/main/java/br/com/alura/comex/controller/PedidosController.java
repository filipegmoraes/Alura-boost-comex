package br.com.alura.comex.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.controller.dto.PedidoDto;
import br.com.alura.comex.controller.form.PedidoForm;
import br.com.alura.comex.service.ClienteService;
import br.com.alura.comex.service.PedidoService;
import br.com.alura.comex.service.ProdutoService;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

	PedidoService pedidoService;
	ClienteService clienteService;
	ProdutoService produtoService;
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "PedidosPorCategoria", allEntries = true)
	public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder){
		if(clienteService.findById(form.getClienteId()) != null
				&& produtoService.findAllById(form.getItensForms(), form.getListId()) != null) {
			PedidoDto pedidoDto = pedidoService.save(form);
			URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(pedidoDto.getId()).toUri();
			return ResponseEntity.created(uri).body(pedidoDto);
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> detalhar(@PathVariable Long id) {
		if(pedidoService.findById(id) != null) {
			return ResponseEntity.ok(pedidoService.findById(id));
		}
		return ResponseEntity.notFound().build();
	}
	
}
