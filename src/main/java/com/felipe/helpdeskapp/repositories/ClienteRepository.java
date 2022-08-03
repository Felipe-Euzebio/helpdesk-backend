package com.felipe.helpdeskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.helpdeskapp.domain.Pessoa;

public interface ClienteRepository extends JpaRepository<Pessoa, Integer>{

	
	
}
