package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Empresa;

@Repository("empresaRepository")
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	Empresa findByEmail(String email);

	//List<Empresa> findEmpresaByUser(Empresa empresa);
}
