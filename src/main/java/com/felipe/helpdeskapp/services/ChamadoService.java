package com.felipe.helpdeskapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.helpdeskapp.domain.Chamado;
import com.felipe.helpdeskapp.repositories.ChamadoRepository;
import com.felipe.helpdeskapp.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	public Chamado findById(Integer id) {
		
		Optional<Chamado> obj = chamadoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado. Id: " + id));
		
	}


	public List<Chamado> findAll() {
		
		return chamadoRepository.findAll();
		
	}
	
	
	
	
}
