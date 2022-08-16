package com.felipe.helpdeskapp.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipe.helpdeskapp.domain.Pessoa;
import com.felipe.helpdeskapp.domain.Cliente;
import com.felipe.helpdeskapp.domain.dtos.ClienteDTO;
import com.felipe.helpdeskapp.repositories.PessoaRepository;
import com.felipe.helpdeskapp.repositories.ClienteRepository;
import com.felipe.helpdeskapp.services.exceptions.DataIntegrityViolationException;
import com.felipe.helpdeskapp.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public Cliente create(ClienteDTO objDTO) {

		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		
		validarPorCpfEEmail(objDTO);
		
		Cliente newObj = new Cliente(objDTO);
		
		return clienteRepository.save(newObj);
		
	}

	
	public List<Cliente> findAll() {
		
		return clienteRepository.findAll();
		
	}

	
	public Cliente findById(Integer id) {
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id));
		
	}


	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		
		objDTO.setId(id);
		
		Cliente oldObj = findById(id);
		
		if (!objDTO.getSenha().equals(oldObj.getSenha())) {
			
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));
			
		}
		
		validarPorCpfEEmail(objDTO);
		
		oldObj = new Cliente(objDTO);
		
		return clienteRepository.save(oldObj);
		
	}
	
	
	public void delete(Integer id) {

		Cliente obj = findById(id);
		
		if (obj.getChamados().size() > 0) {
			
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado");
			
		}
			
		clienteRepository.deleteById(id);
			
	}
	
	
	private void validarPorCpfEEmail(ClienteDTO objDTO) {
		
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
