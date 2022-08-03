package com.felipe.helpdeskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.helpdeskapp.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

	
	
}
