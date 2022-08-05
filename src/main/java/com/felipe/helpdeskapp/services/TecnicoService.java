package com.felipe.helpdeskapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.helpdeskapp.domain.Tecnico;
import com.felipe.helpdeskapp.domain.dtos.TecnicoDTO;
import com.felipe.helpdeskapp.repositories.TecnicoRepository;
import com.felipe.helpdeskapp.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	
	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id));
		
	}

	
	public List<Tecnico> findAll() {
		
		return tecnicoRepository.findAll();
		
	}

	
	public Tecnico create(TecnicoDTO objDTO) {

		objDTO.setId(null);
		
		Tecnico newObj = new Tecnico(objDTO);
		
		return tecnicoRepository.save(newObj);
		
	}
	
}
