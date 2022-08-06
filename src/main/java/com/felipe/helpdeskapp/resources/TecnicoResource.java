package com.felipe.helpdeskapp.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipe.helpdeskapp.domain.Tecnico;
import com.felipe.helpdeskapp.domain.dtos.TecnicoDTO;
import com.felipe.helpdeskapp.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	//	Injentando os serviços
	@Autowired
	private TecnicoService tecnicoService;
	
	
	//	ResponseEntity — Representa toda resposta HTTP
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		
		Tecnico obj = tecnicoService.findById(id);
		
		// Retornando o DTO ao invés da entidade Técnico
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		
		List<Tecnico> list = tecnicoService.findAll();
		
		List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
	
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
			
		Tecnico newObj = tecnicoService.create(objDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,
			@Valid @RequestBody TecnicoDTO objDTO) {
		
		Tecnico updatedObj = tecnicoService.update(id, objDTO);
		
		return ResponseEntity.ok().body(new TecnicoDTO(updatedObj));
		
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id) {
		
		tecnicoService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
