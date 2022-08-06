package com.felipe.helpdeskapp.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.helpdeskapp.domain.Chamado;
import com.felipe.helpdeskapp.domain.Cliente;
import com.felipe.helpdeskapp.domain.Tecnico;
import com.felipe.helpdeskapp.domain.dtos.ChamadoDTO;
import com.felipe.helpdeskapp.domain.enums.Prioridade;
import com.felipe.helpdeskapp.domain.enums.Status;
import com.felipe.helpdeskapp.repositories.ChamadoRepository;
import com.felipe.helpdeskapp.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public Chamado findById(Integer id) {
		
		Optional<Chamado> obj = chamadoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado. Id: " + id));
		
	}


	public List<Chamado> findAll() {
		
		return chamadoRepository.findAll();
		
	}


	public Chamado create(@Valid ChamadoDTO objDTO) {
		
		return chamadoRepository.save(newChamado(objDTO));
		
	}
	
	
	private Chamado newChamado(ChamadoDTO obj) {
		
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Chamado chamado = new Chamado();
		
		if (obj.getId() != null) {
			
			chamado.setId(obj.getId());
			
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		
		return chamado;
		
	}
	
}
