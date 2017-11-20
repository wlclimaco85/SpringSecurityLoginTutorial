package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Jogo;

@Repository("jogoRepository")
public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
//	Empresa findByEmail(String email);

	//List<Empresa> findEmpresaByUser(Empresa empresa);
}
