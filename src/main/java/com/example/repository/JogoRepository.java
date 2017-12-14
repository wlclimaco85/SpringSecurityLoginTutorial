package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Jogo;
import com.example.model.JogoPorData;

@Repository("jogoRepository")
public interface JogoRepository extends JpaRepository<Jogo, Long> {

	void save(List<JogoPorData> jogos);
	
//	Empresa findByEmail(String email);

	//List<Empresa> findEmpresaByUser(Empresa empresa);
}
