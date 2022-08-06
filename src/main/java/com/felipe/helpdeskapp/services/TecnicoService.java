package com.felipe.helpdeskapp.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.helpdeskapp.domain.Pessoa;
import com.felipe.helpdeskapp.domain.Tecnico;
import com.felipe.helpdeskapp.domain.dtos.TecnicoDTO;
import com.felipe.helpdeskapp.repositories.PessoaRepository;
import com.felipe.helpdeskapp.repositories.TecnicoRepository;
import com.felipe.helpdeskapp.services.exceptions.DataIntegrityViolationException;
import com.felipe.helpdeskapp.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Tecnico create(TecnicoDTO objDTO) {

		objDTO.setId(null);
		
		validarPorCpfEEmail(objDTO);
		
		Tecnico newObj = new Tecnico(objDTO);
		
		return tecnicoRepository.save(newObj);
		
	}

	
	public List<Tecnico> findAll() {
		
		return tecnicoRepository.findAll();
		
	}

	
	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado. Id: " + id));
		
	}


	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		
		objDTO.setId(id);
		
		Tecnico oldObj = findById(id);
		
		validarPorCpfEEmail(objDTO);
		
		oldObj = new Tecnico(objDTO);
		
		return tecnicoRepository.save(oldObj);
		
	}
	
	
	public void delete(Integer id) {

		Tecnico obj = findById(id);
		
		if (obj.getChamados().size() > 0) {
			
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado");
			
		}
			
		tecnicoRepository.deleteById(id);
			
	}
	
	
	private void validarPorCpfEEmail(TecnicoDTO objDTO) {
		
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
			
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			
			throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
			
		}
		
	}
	
}
