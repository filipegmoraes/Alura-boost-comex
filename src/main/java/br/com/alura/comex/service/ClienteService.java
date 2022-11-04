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

import br.com.alura.comex.controller.dto.ClienteDto;
import br.com.alura.comex.controller.form.ClienteForm;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;

	public ClienteDto save(ClienteForm clienteForm) {
		Cliente cliente = new Cliente();
		cliente.setBairro(clienteForm.getBairro());
		cliente.setCidade(clienteForm.getCidade());
		cliente.setComplemento(clienteForm.getComplemento());
		cliente.setCpf(clienteForm.getCpf());
		cliente.setEstado(clienteForm.getEstado());
		cliente.setNome(clienteForm.getNome());
		cliente.setNumero(clienteForm.getNumero());
		cliente.setRua(clienteForm.getRua());
		cliente.setTelefone(clienteForm.getTelefone());
		return new ClienteDto(clienteRepository.save(cliente));
	}
	
	public ClienteDto findById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isPresent()) {
			return new ClienteDto(cliente.get());
		}
		return null;
	}
	
	public List<ClienteDto> findAll(int page){
		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Cliente> clientes = clienteRepository.findAll(pageable);
		List<ClienteDto> clienteDto = new ArrayList<>();
		clientes.forEach(cliente -> clienteDto.add(new ClienteDto(cliente)));
		return clienteDto;
	}
}
