package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.JogoPorData;

@Repository("jogoPorDataRepository")
public interface JogoPorDataRepository extends JpaRepository<JogoPorData, Long> {

	//void save(List<JogoPorData> jogos);
	
//	Empresa findByEmail(String email);

	//List<Empresa> findEmpresaByUser(Empresa empresa);
}
