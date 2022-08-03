package com.felipe.helpdeskapp.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.helpdeskapp.domain.Chamado;
import com.felipe.helpdeskapp.domain.Cliente;
import com.felipe.helpdeskapp.domain.Tecnico;
import com.felipe.helpdeskapp.domain.enums.Perfil;
import com.felipe.helpdeskapp.domain.enums.Prioridade;
import com.felipe.helpdeskapp.domain.enums.Status;
import com.felipe.helpdeskapp.repositories.ChamadoRepository;
import com.felipe.helpdeskapp.repositories.ClienteRepository;
import com.felipe.helpdeskapp.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instanciaDB() {
		
		Tecnico tecnico1 = new Tecnico(null, "Valdir Cezar", "381.880.360-99", "valdir@gmail.com", "123");
		tecnico1.addPerfil(Perfil.ADMIN);
		
		Cliente cliente1 = new Cliente(null, "Linus Torvalds", "507.326.810-57", "torvalds@gmail.com", "123");
		
		Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tecnico1, cliente1);
		
		
		tecnicoRepository.saveAll(Arrays.asList(tecnico1));
		
		clienteRepository.saveAll(Arrays.asList(cliente1));
		
		chamadoRepository.saveAll(Arrays.asList(chamado1));
		
	}
	
}
