package com.felipe.helpdeskapp.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipe.helpdeskapp.domain.Cliente;
import com.felipe.helpdeskapp.domain.dtos.ClienteDTO;
import com.felipe.helpdeskapp.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
@CrossOrigin
public class ClienteResource {

	//	Injentando os serviços
	@Autowired
	private ClienteService clienteService;
	
	
	//	ResponseEntity — Representa toda resposta HTTP
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		
		Cliente obj = clienteService.findById(id);
		
		// Retornando o DTO ao invés da entidade Técnico
		return ResponseEntity.ok().body(new ClienteDTO(obj));
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<Cliente> list = clienteService.findAll();
		
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
	
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO) {
			
		Cliente newObj = clienteService.create(objDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,
			@Valid @RequestBody ClienteDTO objDTO) {
		
		Cliente updatedObj = clienteService.update(id, objDTO);
		
		return ResponseEntity.ok().body(new ClienteDTO(updatedObj));
		
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {
		
		clienteService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
