package com.felipe.helpdeskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.helpdeskapp.domain.Cliente;

public interface PessoaRepository extends JpaRepository<Cliente, Integer>{

	
	
}
