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
		
		Tecnico tecnico2 = new Tecnico(null, "Felipe Euzébio", "994.923.060-85", "felipe@gmail.com", "123");
		tecnico2.addPerfil(Perfil.ADMIN);
		
		Tecnico tecnico3 = new Tecnico(null, "José Rômulo", "325.293.170-20", "joseromulo@gmail.com", "123");
		tecnico3.addPerfil(Perfil.ADMIN);
		
		
		Cliente cliente1 = new Cliente(null, "Linus Torvalds", "507.326.810-57", "torvalds@gmail.com", "123");
		
		Cliente cliente2 = new Cliente(null, "Steve Jobs", "880.338.890-71", "stevejobs@gmail.com", "123");
		
		Cliente cliente3 = new Cliente(null, "James Gosling", "770.082.580-70", "jamesgosling@gmail.com", "123");
		
		
		Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tecnico1, cliente1);
		
		Chamado chamado2 = new Chamado(null, Prioridade.ALTA, Status.ENCERRADO, "Chamado 02", "Segundo chamado", tecnico2, cliente2);
		
		Chamado chamado3 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 03", "Terceiro chamado", tecnico3, cliente3);
		
		
		tecnicoRepository.saveAll(Arrays.asList(tecnico1, tecnico2, tecnico3));
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));
		
		chamadoRepository.saveAll(Arrays.asList(chamado1, chamado2, chamado3));
		
	}
	
}
