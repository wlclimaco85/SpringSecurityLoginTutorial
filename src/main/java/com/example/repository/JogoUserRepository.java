package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.UserJogo2;

@Repository("jogoUserRepository")
public interface JogoUserRepository extends JpaRepository<UserJogo2, Long> {

	//void save(List<JogoPorData> jogos);
	
//	Empresa findByEmail(String email);

	//List<Empresa> findEmpresaByUser(Empresa empresa);
}
