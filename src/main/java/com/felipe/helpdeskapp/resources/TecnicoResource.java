package com.felipe.helpdeskapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.helpdeskapp.domain.Tecnico;
import com.felipe.helpdeskapp.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	//	Injentando os serviços
	@Autowired
	private TecnicoService tecnicoService;
	
	
	//	ResponseEntity — Representa toda resposta HTTP
	@GetMapping(value = "/{id}")
	public ResponseEntity<Tecnico> findById(@PathVariable Integer id) {
		
		Tecnico obj = tecnicoService.findById(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
}
