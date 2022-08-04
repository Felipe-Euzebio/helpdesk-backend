package com.felipe.helpdeskapp.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
